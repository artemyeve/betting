package by.artemyeu.betting.logic;

import by.artemyeu.betting.entity.Account;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.manager.Messenger;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;
import by.artemyeu.betting.dao.UserDAO;
import by.artemyeu.betting.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Acer on 16.05.2017.
 */
@SuppressWarnings("Duplicates")
public class UserLogic implements Messenger {

    private final String SUCCESS = "success";


    public String addFunds(User user, String newCash) throws LogicException {

            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            try {
                BigDecimal finalCash = user.getBalance().add(new BigDecimal(newCash));
                userDAO.changeCash(finalCash,user.getId());
                BigDecimal newUserCash = userDAO.findCash(user.getId());
                if (newUserCash.compareTo(BigDecimal.ZERO) > 0) {
                    user.setBalance(newUserCash);
                }
                return SUCCESS;
            } catch (DAOException e) {
                throw new LogicException("Exception during money addition", e);
            } finally {
                userDAO.closeConnection(connection);
            }

    }
    public String changeFirstName(int userId, String newFirstName) throws LogicException {
        Validator validator = new Validator();
        if (validator.isFirstNameValid(newFirstName)) {
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                UserDAO userDAO = new UserDAO(connection);
                try {
                    userDAO.changeFirstName(userId, newFirstName);
                    return SUCCESS;
                } catch (DAOException e) {
                    throw new LogicException("Error during changing first name", e);
                } finally {
                    userDAO.closeConnection(connection);
                }

        } else {
            return messageManager.getProperty(MessageManager.CHANGE_FIRST_NAME_ERROR);
        }
    }
    public String changeSecondName(int userId, String newSecondName) throws LogicException {
        Validator validator = new Validator();
        if (validator.isSecondNameValid(newSecondName)) {
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                UserDAO userDAO = new UserDAO(connection);
                try {
                    userDAO.changeSecondName(userId, newSecondName);
                    return SUCCESS;
                } catch (DAOException e) {
                    throw new LogicException("Error during changing second name", e);
                } finally {
                    userDAO.closeConnection(connection);
                }

        } else {
            return messageManager.getProperty(MessageManager.CHANGE_SECOND_NAME_ERROR);
        }
    }
    public String changeEmail(int userId, String newEmail) throws LogicException {
        Validator validator = new Validator();
        if (validator.isEmailValid(newEmail)) {
            if (validator.isEmailUnique(newEmail)) {
                ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                UserDAO userDAO = new UserDAO(connection);
                try {
                    userDAO.changeEmail(userId, newEmail);
                    return SUCCESS;
                } catch (DAOException e) {
                    throw new LogicException("Error during changing email", e);
                } finally {
                    userDAO.closeConnection(connection);
                }
            } else {
                return messageManager.getProperty(MessageManager.NOT_UNIQUE_EMAIL);
            }
        } else {
            return messageManager.getProperty(MessageManager.CHANGE_EMAIL_ERROR);
        }
    }

    public String changeLogin(int userId, String newLogin) throws LogicException {
        Validator validator = new Validator();
        if (validator.isLoginValid(newLogin)) {
            if (validator.isLoginUnique(newLogin)) {
                ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                UserDAO userDAO = new UserDAO(connection);
                try {
                    userDAO.changeLogin(userId, newLogin);
                    return SUCCESS;
                } catch (DAOException e) {
                    throw new LogicException("Error during changing login", e);
                } finally {
                    userDAO.closeConnection(connection);
                }
            } else {
                return messageManager.getProperty(MessageManager.NOT_UNIQUE_LOGIN);
            }
        } else {
            return messageManager.getProperty(MessageManager.CHANGE_LOGIN_ERROR);
        }
    }

    public String changePass(int userId, String userPass, String password, String newPassword, String confPassword) throws LogicException {
        Validator validator = new Validator();
        String md5Pass = DigestUtils.md5Hex(password);
        if (userPass.equals(md5Pass)) {
            if (validator.isPasswordValid(newPassword)) {
                if (validator.validateConfirmPass(confPassword, newPassword)) {
                    ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                    UserDAO userDAO = new UserDAO(connection);
                    String md5NewPass = DigestUtils.md5Hex(newPassword);
                    try {
                        userDAO.changePassword(userId, md5NewPass);
                        return SUCCESS;
                    } catch (DAOException e) {
                        throw new LogicException("Error during changing password", e);
                    } finally {
                        userDAO.closeConnection(connection);
                    }
                } else {
                    return messageManager.getProperty(MessageManager.CHANGE_PASS_EQUAL_NEW_ERROR);
                }
            } else {
                return messageManager.getProperty(MessageManager.CHANGE_PASS_NEW_ERROR);
            }
        } else {
            return messageManager.getProperty(MessageManager.CHANGE_PASS_EQUAL_ERROR);
        }
    }

    public List<User> findClients() throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            return userDAO.findClients();
        } catch (DAOException e) {
            throw new LogicException("Exception during clients search", e);
        } finally {
            userDAO.closeConnection(connection);
        }
    }

    public List<User> findSuitableUsers(String str) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            List<User> allClients = userDAO.findClients();
            List<User> res = new ArrayList<>();
            for (User temp : allClients) {
                if (temp.getLogin().toLowerCase().contains(str.toLowerCase())) {
                    res.add(temp);
                }
            }
            return res;
        } catch (DAOException e) {
            throw new LogicException("Exception during users search", e);
        } finally {
            userDAO.closeConnection(connection);
        }
    }

    public User findUserByLogin(String login) throws LogicException {
        User user;
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            user = userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            throw new LogicException("Error during user search", e);
        }finally {
            userDAO.closeConnection(connection);
        }
        return user;
    }

    public String singUp(String firstName,String secondName,String login, String password, String confirmPassword, String email) throws LogicException {
        Validator validator = new Validator();
        String res = validator.isDataValid(firstName,secondName,login, password, confirmPassword, email);
        if (SUCCESS.equals(res)) {
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            String md5Pass = DigestUtils.md5Hex(password);
            try {
                userDAO.addUser(firstName,secondName,login, md5Pass, email);
                return SUCCESS;
            } catch (DAOException e) {
                throw new LogicException("Error during signup", e);
            } finally {
                userDAO.closeConnection(connection);
            }
        } else {
            return res;
        }
    }

    private User findUserById(int id) throws LogicException {
        User user;
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            user = userDAO.findUserById(id);
        } catch (DAOException e) {
            throw new LogicException("Exception during user search", e);
        }finally {
            userDAO.closeConnection(connection);
        }
        return user;
    }

}

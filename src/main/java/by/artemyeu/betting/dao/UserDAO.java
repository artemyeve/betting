package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.pool.ProxyConnection;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 06.06.2017.
 */

@SuppressWarnings("Duplicates")
public class UserDAO extends AbstractDAO<User> {

    /** The Constant SQL_ADD_USER. */
    private static final String SQL_ADD_USER = "INSERT INTO user(first_name,second_name,login,password,email) VALUES(?,?,?,?,?)";

       /** The Constant SQL_CHANGE_BALANCE. */
    private static final String SQL_CHANGE_CASH = "UPDATE account SET balance=? WHERE id=?";

    /** The Constant SQL_CHANGE_FIRST_NAME. */
    private static final String SQL_CHANGE_FIRST_NAME = "UPDATE user SET first_name=? WHERE id=?";

    /** The Constant SQL_CHANGE_SECOND_NAME. */
    private static final String SQL_CHANGE_SECOND_NAME = "UPDATE user SET second_name=? WHERE id=?";

    /** The Constant SQL_CHANGE_EMAIL. */
    private static final String SQL_CHANGE_EMAIL = "UPDATE user SET email=? WHERE id=?";

    /** The Constant SQL_CHANGE_LOGIN. */
    private static final String SQL_CHANGE_LOGIN = "UPDATE user SET login=? WHERE id=?";

    /** The Constant SQL_CHANGE_PASS. */
    private static final String SQL_CHANGE_PASS = "UPDATE user SET password=? WHERE id=?";

    /** The Constant SQL_DELETE_USER. */
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id = ?";

    /** The Constant SQL_SELECT_ALL_CLIENTS. */
    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT user.id, user.first_name,user.second_name,user.login,\n"+
            "user.password,user.email FROM user WHERE user.role='bettor'\n"+
            " ORDER BY user.login";

    /** The Constant SQL_SELECT_CASH. */
    private static final String SQL_SELECT_CASH = "SELECT balance FROM account WHERE id=?";

    /** The Constant SQL_SELECT_PASSWORD_BY_LOGIN. */
    private static final String SQL_SELECT_PASSWORD_BY_LOGIN = "SELECT password FROM user WHERE login=?";

    /** The Constant SQL_SELECT_USER_BY_ID. */
    private static final String SQL_SELECT_USER_BY_ID = "SELECT first_name,second_name,login,\n" +
            "password,email FROM user WHERE id=?";

    /** The Constant SQL_SELECT_USER_BY_LOGIN. */
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, first_name,second_name,\n" +
             "password,email FROM user WHERE login=?";

    /** The Constant SQL_SELECT_USER_BY_EMAIL. */
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id, first_name,second_name,login\n" +
            "password FROM user WHERE email=?";

    /**
     * Instantiates a new user DAO.
     *
     * @param connection the connection
     */
    public UserDAO(ProxyConnection connection) {
        super(connection);
    }


    /**
     * Adds the user.
     * @param firstName the first name
     * @param secondName the second name
     * @param login the login
     * @param password the password
     * @param email the email
     * @throws DAOException the DAO exception
     */
    public void addUser(String firstName,String secondName,String login, String password, String email) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_USER);

            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during user addition", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change cash.
     * @param cash the cash
     * @param accountId the account id
     * @throws DAOException the DAO exception
     */
    public void changeCash(BigDecimal cash,int accountId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_CASH);
            statement.setBigDecimal(1, cash);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during cash change", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change first name.
     *
     * @param userId the user id
     * @param newFirstName the new first name
     * @throws DAOException the DAO exception
     */
    public void changeFirstName(int userId, String newFirstName) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_FIRST_NAME);
            statement.setString(1, newFirstName);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing first name", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change second name.
     *
     * @param userId the user id
     * @param newSecondName the new second name
     * @throws DAOException the DAO exception
     */
    public void changeSecondName(int userId, String newSecondName) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_SECOND_NAME);
            statement.setString(1, newSecondName);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing second name", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change email.
     *
     * @param userId the user id
     * @param newEmail the new email
     * @throws DAOException the DAO exception
     */
    public void changeEmail(int userId, String newEmail) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_EMAIL);
            statement.setString(1, newEmail);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing email", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change login.
     *
     * @param userId the user id
     * @param newLogin the new login
     * @throws DAOException the DAO exception
     */
    public void changeLogin(int userId, String newLogin) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_LOGIN);
            statement.setString(1, newLogin);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing login", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change password.
     *
     * @param userId the user id
     * @param newPass the new pass
     * @throws DAOException the DAO exception
     */
    public void changePassword(int userId, String newPass) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_PASS);
            statement.setString(1, newPass);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing password", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Creates the client list.
     *
     * @param set the set
     * @return the list
     * @throws DAOException the DAO exception
     */
    private List<User> createClientList(ResultSet set) throws DAOException {
        List<User> clientList=new ArrayList<>();
        try {
            while (set.next()) {
                int userId = set.getInt("id");
                String login = set.getString("login");
                clientList.add(new User(userId, login));
            }
            return clientList;
        } catch (SQLException e) {
            throw new DAOException("Exception during client list creation", e);
        }
    }

    /**
     * Creates the user.
     *
     * @param set the set
     * @return the user
     * @throws DAOException the DAO exception
     */
    private User createUser(ResultSet set) throws DAOException {
        try {
            if (set.next()) {
                int id = set.getInt("id");
                String firstName = set.getString("firstName");
                String secondName = set.getString("secondName");
                String login = set.getString("login");
                String password = set.getString("password");
                String email = set.getString("email");
                UserRole role = UserRole.valueOf(set.getString("role"));
                return new User(id, firstName,secondName,login, password,email, role);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during user creation", e);
        }
    }

    /**
     * Find clients.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<User> findClients()throws DAOException{
        List<User> userList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_CLIENTS);
            ResultSet set = statement.executeQuery();
            userList = createClientList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during clients search",e);
        } finally {
            closeStatement(statement);
        }
        return userList;
    }

    /**
     * Find cash.
     *
     * @param accountId the account id
     * @return the BigDecimal
     * @throws DAOException the DAO exception
     */
    public BigDecimal findCash(int accountId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CASH);
            statement.setInt(1, accountId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getBigDecimal("cash_account");
            } else {
                return BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during cash search", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Find password.
     *
     * @param login the login
     * @return the string
     * @throws DAOException the DAO exception
     */
    public String findPassword(String login) throws DAOException {
        String password = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                password = set.getString(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during user password by login search", e);
        } finally {
            closeStatement(statement);
        }
        return password;
    }

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the user
     * @throws DAOException the DAO exception
     */
    public User findUserByLogin(String login) throws DAOException {
        User user;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            user = createUser(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during user by login search", e);
        } finally {
            closeStatement(statement);
        }
        return user;
    }

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the user
     * @throws DAOException the DAO exception
     */
    public User findUserByEmail(String email) throws DAOException {
        User user;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            user = createUser(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during user by email search", e);
        } finally {
            closeStatement(statement);
        }
        return user;
    }

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws DAOException the DAO exception
     */
    public User findUserById(int id) throws DAOException {
        User user;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            user = createUser(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during user by id search", e);
        } finally {
            closeStatement(statement);
        }
        return user;
    }

}


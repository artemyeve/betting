package by.artemyeu.betting.logic;

import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;
import by.artemyeu.betting.dao.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;



/**
 * Created by Acer on 16.05.2017.
 */
public class LoginLogic {

    /**
     * Check login.
     *
     * @param login the login
     * @param password the password
     * @return true, if successful
     * @throws LogicException the logic exception
     */
    public boolean checkLogin(String login, String password) throws LogicException {
        Validator validator = new Validator();
        if (!validator.isLoginValid(login) || !validator.isPasswordValid(password)) {
            return false;
        }
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        UserDAO userDAO = new UserDAO(connection);
        String md5Pass = DigestUtils.md5Hex(password);
        try {
            String dbPass = userDAO.findPassword(login);
            if (md5Pass.equals(dbPass)) {
                return true;
            }
        } catch (DAOException e) {
            throw new LogicException("Exception during login", e);
        } finally {
            userDAO.closeConnection(connection);
        }
        return false;
    }

}

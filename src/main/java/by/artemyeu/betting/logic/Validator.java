package by.artemyeu.betting.logic;

import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.manager.Messenger;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;
import by.artemyeu.betting.dao.UserDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Acer on 16.05.2017.
 */


public class Validator implements Messenger {

    /**
     * The max cash length.
     */
    private final int MAX_CASH_LENGTH = 5;

    /**
     * The max pass length.
     */
    private final int MAX_PASS_LENGTH = 10;

    /**
     * The min pass length.
     */
    private final int MIN_PASS_LENGTH = 6;

    /**
     * The regex first name.
     */
    private final String REGEX_FIRST_NAME = "(\\w){1,15}";
    /**
     * The regex first name.
     */
    private final String REGEX_SECOND_NAME = "(\\w){1,15}";
    /**
     * The regex login.
     */
    private final String REGEX_LOGIN = "(\\w){6,10}";

    /**
     * The regex email.
     */
    private final String REGEX_EMAIL = "(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})";

    /**
     * The signup success.
     */
    private final String SIGNUP_SUCCESS = "success";

    /**
     * The zero.
     */
    private final int ZERO = 0;


    /**
     * Checks if is cash valid.
     *
     * @param cash the cash
     * @return true, if is cash valid
     */
    public boolean isCashValid(String cash) {
        return !(cash.length() == ZERO && cash.length() > MAX_CASH_LENGTH) && canConvertToUnsignedDouble(cash);
    }

    /**
     * Checks if is first name valid.
     *
     * @param firstName the first name
     * @return true, if is first name valid
     */
    boolean isFirstNameValid(String firstName) {
        Pattern pattern = Pattern.compile(REGEX_FIRST_NAME);
        Matcher matcher = pattern.matcher(firstName);
        return matcher.matches();
    }

    /**
     * Checks if is second name valid.
     *
     * @param secondName the second name
     * @return true, if is second name valid
     */
    boolean isSecondNameValid(String secondName) {
        Pattern pattern = Pattern.compile(REGEX_SECOND_NAME);
        Matcher matcher = pattern.matcher(secondName);
        return matcher.matches();
    }


    /**
     * Checks if is data valid.
     *
     * @param firstName   the firstName
     * @param secondName  the secondName
     * @param login       the login
     * @param password    the password
     * @param confirmPass the confirm pass
     * @param email       the email
     * @return the string
     * @throws LogicException the logic exception
     */
    public String isDataValid(String firstName, String secondName, String login, String password, String confirmPass, String email) throws LogicException {

        if (isFirstNameValid(firstName) && isSecondNameValid(secondName) && isLoginValid(login) && isPasswordValid(password) && isEmailValid(email)
                && validateConfirmPass(confirmPass, password)) {
            if (!isLoginUnique(login)) {
                return messageManager.getProperty(MessageManager.NOT_UNIQUE_LOGIN);
            }
            if (!isEmailUnique(email)) {
                return messageManager.getProperty(MessageManager.NOT_UNIQUE_EMAIL);
            }
            return SIGNUP_SUCCESS;
        } else {
            return messageManager.getProperty(MessageManager.SIGNUP_ERROR);
        }
    }


    /**
     * Checks if is email unique.
     *
     * @param email the email
     * @return true, if is email unique
     * @throws LogicException the logic exception
     */
    boolean isEmailUnique(String email) throws LogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        ProxyConnection connection = pool.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            return (userDAO.findUserByEmail(email) == null);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            userDAO.closeConnection(connection);
        }
    }

    /**
     * Checks if is email valid.
     *
     * @param email the email
     * @return true, if is email valid
     */
    boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks if is login unique.
     *
     * @param login the login
     * @return true, if is login unique
     * @throws LogicException the logic exception
     */
    boolean isLoginUnique(String login) throws LogicException {
        ConnectionPool pool = ConnectionPool.getInstance();
        ProxyConnection connection = pool.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        try {
            return (userDAO.findUserByLogin(login) == null);
        } catch (DAOException e) {
            throw new LogicException("Error during checking login uniqueness", e);
        } finally {
            userDAO.closeConnection(connection);
        }
    }

    /**
     * Checks if is login valid.
     *
     * @param login the login
     * @return true, if is login valid
     */
    boolean isLoginValid(String login) {
        Pattern pattern = Pattern.compile(REGEX_LOGIN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    /**
     * Checks if is password valid.
     *
     * @param password the password
     * @return true, if is password valid
     */
    boolean isPasswordValid(String password) {
        return (password.length() >= MIN_PASS_LENGTH && password.length() <= MAX_PASS_LENGTH);
    }


    /**
     * Validate confirm pass.
     *
     * @param confirmPass the confirm pass
     * @param pass        the pass
     * @return true, if successful
     */
    boolean validateConfirmPass(String confirmPass, String pass) {
        return pass.equals(confirmPass);
    }

    /**
     * Can convert to unsigned double.
     *
     * @param value the value
     * @return true, if successful
     */
    private boolean canConvertToUnsignedDouble(String value) {
        try {
            Double doubleValue = Double.valueOf(value);
            return (doubleValue > ZERO);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
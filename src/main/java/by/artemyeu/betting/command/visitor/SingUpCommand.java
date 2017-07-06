package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 06.06.2017.
 */
public class SingUpCommand extends AbstractCommand {

    /** The Constant PARAM_FIRST_NAME. */
    private static final String PARAM_FIRST_NAME = "firstName";
    /** The Constant PARAM_SECOND_NAME. */
    private static final String PARAM_SECOND_NAME = "secondName";

    /** The Constant PARAM_LOGIN. */
    private static final String PARAM_LOGIN = "login";

    /** The Constant PARAM_PASSWORD. */
    private static final String PARAM_PASSWORD = "password";

    /** The Constant PARAM_CONF_PASS. */
    private static final String PARAM_CONF_PASS = "password2";

    /** The Constant PARAM_EMAIL. */
    private static final String PARAM_EMAIL = "email";

      /** The Constant ROLE_ATTRIBUTE. */
    private static final String ROLE_ATTRIBUTE = "role";

    /** The Constant IS_LOGIN. */
    private static final String IS_LOGIN = "is_login";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String firstName = sessionRequestContent.getRequestParameter(PARAM_FIRST_NAME);
        String secondName = sessionRequestContent.getRequestParameter(PARAM_SECOND_NAME);
        String login = sessionRequestContent.getRequestParameter(PARAM_LOGIN);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        String confPassword = sessionRequestContent.getRequestParameter(PARAM_CONF_PASS);
        String email = sessionRequestContent.getRequestParameter(PARAM_EMAIL);
        UserLogic userLogic = new UserLogic();
        try {
            String res = userLogic.singUp(firstName,secondName,login, password, confPassword, email);
            if (SUCCESS.equals(res)) {
                User user=userLogic.findUserByLogin(login);
                sessionRequestContent.setSessionAttribute(IS_LOGIN, "true");
                sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, user);
                sessionRequestContent.setSessionAttribute(ROLE_ATTRIBUTE, user.getRole());
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            }else{
                sessionRequestContent.setRequestAttribute(PARAM_FIRST_NAME, firstName);
                sessionRequestContent.setRequestAttribute(PARAM_SECOND_NAME, secondName);
                sessionRequestContent.setRequestAttribute(PARAM_LOGIN, login);
                sessionRequestContent.setRequestAttribute(PARAM_PASSWORD, password);
                sessionRequestContent.setRequestAttribute(PARAM_CONF_PASS, confPassword);
                sessionRequestContent.setRequestAttribute(PARAM_EMAIL, email);
                sessionRequestContent.setRequestAttribute(ERROR, res);
                page = ConfigurationManager.getProperty(ConfigurationManager.SIGNUP_PATH);
            }
        } catch (LogicException e) {
            LOG.error("Exception during sign up command", e);
            page = redirectToErrorPage(sessionRequestContent,e);
        }
        return page;
    }
}

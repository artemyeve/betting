package by.artemyeu.betting.command.user;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */
public class ChangeCommand extends AbstractCommand {

    /** The Constant USER_ATTR. */
    private static final String USER_ATTR = "user";
    /** The Constant PARAM_FIRST_NAME. */
    private static final String PARAM_FIRST_NAME = "first_name";
    /** The Constant PARAM_SECOND_NAME. */
    private static final String PARAM_SECOND_NAME = "second_name";

    /** The Constant PARAM_LOGIN. */
    private static final String PARAM_LOGIN = "login";

    /** The Constant PARAM_EMAIL. */
    private static final String PARAM_EMAIL = "email";


    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String logined = (String) sessionRequestContent.getSessionAttribute(IS_LOGIN);
        if (logined != null && Boolean.valueOf(logined)) {
            User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTR);
            String firstName = sessionRequestContent.getRequestParameter(PARAM_FIRST_NAME);
            String secondName = sessionRequestContent.getRequestParameter(PARAM_SECOND_NAME);
            String login = sessionRequestContent.getRequestParameter(PARAM_LOGIN);
            String email = sessionRequestContent.getRequestParameter(PARAM_EMAIL);
            UserLogic userLogic = new UserLogic();
            String res;
            try {
                if (!firstName.equals(user.getFirstName())) {
                    res = userLogic.changeFirstName(user.getId(), firstName);
                    if (SUCCESS.equals(res)) {
                        user.setFirstName(firstName);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.CHANGE_PATH);
                    }
                }
                if (!login.equals(user.getLogin())) {
                    res = userLogic.changeLogin(user.getId(), login);
                    if (SUCCESS.equals(res)) {
                        user.setLogin(login);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.CHANGE_PATH);
                    }
                }
                if (!email.equals(user.getEmail())) {
                    res = userLogic.changeEmail(user.getId(), email);
                    if (SUCCESS.equals(res)) {
                        user.setEmail(email);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.CHANGE_PATH);
                    }
                }
                sessionRequestContent.setSessionAttribute(USER_ATTR, user);
                sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.CHANGE_SUCCESS));
                page = ConfigurationManager.getProperty(ConfigurationManager.PROFILE_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during change command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

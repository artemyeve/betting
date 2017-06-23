package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.logic.LoginLogic;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by 123 on 28.12.2016.
 */
public class LogInCommand extends AbstractCommand {

    /** The Constant PARAM_LOGIN. */
    private static final String PARAM_LOGIN = "login";

    /** The Constant PARAM_PASSWORD. */
    private static final String PARAM_PASSWORD = "password";

    /** The Constant TRUE. */
    private static final String TRUE="true";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String login = sessionRequestContent.getRequestParameter(PARAM_LOGIN);
        String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
        try {
            if (new LoginLogic().checkLogin(login, password)) {
                sessionRequestContent.setSessionAttribute(IS_LOGIN, TRUE);
                UserLogic userLogic = new UserLogic();
                User user = userLogic.findUserByLogin(login);
                sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, user);
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            } else {
                sessionRequestContent.setRequestAttribute(PARAM_LOGIN, login);
                sessionRequestContent.setRequestAttribute(PARAM_PASSWORD, password);
                sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.LOGIN_ERROR));
                page = ConfigurationManager.getProperty(ConfigurationManager.LOGIN_PATH);
            }
        } catch (LogicException e) {
            LOG.error("Exception during login command", e);
            page= redirectToErrorPage(sessionRequestContent,e);
        }
        return page;
    }
}

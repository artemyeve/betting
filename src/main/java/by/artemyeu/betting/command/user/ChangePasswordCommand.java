package by.artemyeu.betting.command.user;

import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */
public class ChangePasswordCommand extends AbstractCommand {

    /** The Constant PARAM_PASSWORD. */
    private static final String PARAM_PASSWORD = "password";

    /** The Constant PARAM_CONF_PASS. */
    private static final String PARAM_CONF_PASS = "password2";

    /** The Constant PARAM_NEW_PASS. */
    private static final String PARAM_NEW_PASS = "new_pass";


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
            User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
            String password = sessionRequestContent.getRequestParameter(PARAM_PASSWORD);
            String newPassword = sessionRequestContent.getRequestParameter(PARAM_NEW_PASS);
            String confPassword = sessionRequestContent.getRequestParameter(PARAM_CONF_PASS);
            UserLogic userLogic = new UserLogic();
            String res;
            try {
                res = userLogic.changePass(user.getId(), user.getPassword(), password, newPassword, confPassword);
                if (SUCCESS.equals(res)) {
                    user.setPassword(newPassword);
                    sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.CHANGE_SUCCESS));
                    sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, user);
                    page = ConfigurationManager.getProperty(ConfigurationManager.PROFILE_PATH);
                } else {
                    sessionRequestContent.setRequestAttribute(ERROR, res);
                    return ConfigurationManager.getProperty(ConfigurationManager.CHANGE_PASS_PATH);
                }
            } catch (LogicException e) {
                LOG.error("Exception during change password command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

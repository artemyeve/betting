package by.artemyeu.betting.command.user;

import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */
public class AddFundsCommand extends AbstractCommand {

    /** The Constant PARAM_CASH. */
    private static final String PARAM_CASH = "cash";


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String logined = (String) sessionRequestContent.getSessionAttribute(IS_LOGIN);
        if (logined != null && Boolean.valueOf(logined)) {
            User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
            String cash = sessionRequestContent.getRequestParameter(PARAM_CASH);
            UserLogic userLogic = new UserLogic();
                try {
                    userLogic.addFunds(user, cash);
                    sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.CHANGE_SUCCESS));
                    sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, user);
                    page = ConfigurationManager.getProperty(ConfigurationManager.PROFILE_PATH);
                } catch (LogicException e) {
                    LOG.error("Exception funds addition command", e);
                    page = redirectToErrorPage(sessionRequestContent, e);
                }

        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

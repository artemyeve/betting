package by.artemyeu.betting.command.user;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.BetLogic;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 04.07.2017.
 */
public class BetCommand extends AbstractCommand {

    /** The Constant PARAM_BET_AMOUNT. */
    private static final String PARAM_BET_AMOUNT = "bet_amount";

    private final String MATCH_ID_PARAMETER = "match_id";

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
            String betAmount = sessionRequestContent.getRequestParameter(PARAM_BET_AMOUNT);
            BetLogic betLogic = new BetLogic();
            try {
                betLogic.addBet(user, betAmount);
                sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.CHANGE_SUCCESS));
                sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, user);
                page = ConfigurationManager.getProperty(ConfigurationManager.PROFILE_PATH);
            } catch (LogicException e) {
                LOG.error("Exception bet addition command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }

        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

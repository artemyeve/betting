package by.artemyeu.betting.command.bookmaker;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.BetLineLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 01.06.2017.
 */
public class DeleteOutcomeCommand extends AbstractCommand {

    private final String BET_LINE_ID_PARAMETER = "betLineId";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
        if (user != null && user.getRole() == UserRole.BOOKMAKER) {
            int betLineId = Integer.valueOf(sessionRequestContent.getRequestParameter(BET_LINE_ID_PARAMETER));
            BetLineLogic betLineLogic = new BetLineLogic();
            try {
                betLineLogic.deleteOutcome(betLineId);
                sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.DELETE_OUTCOME_SUCCESS));
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during outcome delete command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

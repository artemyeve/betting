package by.artemyeu.betting.command.admin;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */
public class DeleteMatchCommand extends AbstractCommand {

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
        User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
        if (user != null && user.getRole() == UserRole.ADMIN) {
            int matchId = Integer.valueOf(sessionRequestContent.getRequestParameter(MATCH_ID_PARAMETER));
            MatchLogic matchLogic = new MatchLogic();
            try {
                matchLogic.deleteMatchById(matchId);
                sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.DELETE_MATCH_SUCCESS));
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during match delete command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

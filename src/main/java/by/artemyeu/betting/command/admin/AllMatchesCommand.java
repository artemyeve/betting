package by.artemyeu.betting.command.admin;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.Match;

import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.List;

/**
 * Created by Acer on 01.06.2017.
 */
public class AllMatchesCommand extends AbstractCommand {

    private final String MATCH_LIST_ATTR = "matches";
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
            MatchLogic matchLogic = new MatchLogic();
            try {
                List<Match> matchList = matchLogic.findAllMatches();
                sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during matches search", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }

}

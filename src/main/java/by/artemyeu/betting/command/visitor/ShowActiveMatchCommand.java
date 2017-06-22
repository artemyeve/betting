package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;
import by.artemyeu.betting.logic.MatchLogic;

import java.util.List;

/**
 * Created by Acer on 06.06.2017.
 */
public class ShowActiveMatchCommand extends AbstractCommand {

    /** The find parameter. */
    private final String FIND_PARAMETER = "find";

    /** The search attr. */
    private final String SEARCH_ATTR = "search";

    /** The match list attr. */
    private final String MATCH_LIST_ATTR="match_list";

    /** The is deleted. */
    private final String IS_DELETED = "is_deleted";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        MatchLogic matchLogic = new MatchLogic();
        try {
            List<Match> matchList = matchLogic.findActiveMatches();
            sessionRequestContent.setSessionAttribute(IS_DELETED, false);
            sessionRequestContent.setRequestAttribute(SEARCH_ATTR, true);
            sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
            page = ConfigurationManager.getProperty(ConfigurationManager.MAIN_PATH);
        } catch (LogicException e) {
            LOG.error("Exception during active matches search", e);
            page = redirectToErrorPage(sessionRequestContent, e);
        }
        return page;
    }
}

package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.List;

/**
 * Created by 123 on 25.01.2017.
 */
public class AllMatchesCommand extends AbstractCommand{

    /** The match list attr. */
    private final String MATCH_LIST_ATTR = "match_list";

    /** The is deleted. */
    private final String IS_DELETED = "is_deleted";

    /** The all attr. */
    private final String ALL_ATTR="all";

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
            List<Match> matchList = matchLogic.findAllMatches();
            sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
            sessionRequestContent.setSessionAttribute(IS_DELETED, false);
            sessionRequestContent.setRequestAttribute(ALL_ATTR,true);
            page = ConfigurationManager.getProperty(ConfigurationManager.MAIN_PATH);
        } catch (LogicException e) {
            LOG.error("Exception during all matches search",e);
            page = redirectToErrorPage(sessionRequestContent, e);
        }
        return page;
    }
}

package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.List;

/**
 * Created by Acer on 21.06.2017.
 */
public class ShowMatchDateCommand extends AbstractCommand {
    /** The match list attr. */
    private final String MATCH_LIST_ATTR = "match_list";

    /** The is deleted. */
    private final String IS_DELETED = "is_deleted";

    /** The genre parameter. */
    private final String MATCH_DATE_PARAMETER="match_date";

    /** The is genre. */
    private final String IS_TOURNAMENT="is_match_date";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page=null;
        /*String matchDate= sessionRequestContent.getRequestParameter(MATCH_DATE_PARAMETER);
        if(matchDate==null){
            matchDate= sessionRequestContent.getSessionAttribute(MATCH_DATE_PARAMETER).toString();
        }
        MatchLogic matchLogic = new MatchLogic();
        try {
            List<Match> matchList = matchLogic.findMatchByDate(matchDate);
            sessionRequestContent.setSessionAttribute(MATCH_DATE_PARAMETER,matchDate);
            sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
            sessionRequestContent.setSessionAttribute(IS_DELETED, false);
            sessionRequestContent.setRequestAttribute(IS_TOURNAMENT,true);
            page = ConfigurationManager.getProperty(ConfigurationManager.MAIN_PATH);
        } catch (LogicException e) {
            LOG.error("Exception during matches by match date search",e);
            page = redirectToErrorPage(sessionRequestContent, e);
        }*/
        return page;
    }
}

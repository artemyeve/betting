package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;


import java.util.List;

/**
 * Created by 123 on 25.01.2017.
 */
public class ShowTournamentCommand extends AbstractCommand {

    /** The match list attr. */
    private final String MATCH_LIST_ATTR = "match_list";

    /** The is deleted. */
    private final String IS_DELETED = "is_deleted";

    /** The genre parameter. */
    private final String TOURNAMENT_PARAMETER="tournament";

    /** The is genre. */
    private final String IS_TOURNAMENT="is_tournament";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String tournament= sessionRequestContent.getRequestParameter(TOURNAMENT_PARAMETER);
        if(tournament==null){
            tournament= sessionRequestContent.getSessionAttribute(TOURNAMENT_PARAMETER).toString();
        }
        MatchLogic matchLogic = new MatchLogic();
        try {
            List<Match> matchList = matchLogic.findMatchesByTournament(tournament);
            sessionRequestContent.setSessionAttribute(TOURNAMENT_PARAMETER,tournament);
            sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
            sessionRequestContent.setSessionAttribute(IS_DELETED, false);
            sessionRequestContent.setRequestAttribute(IS_TOURNAMENT,true);
            page = ConfigurationManager.getProperty(ConfigurationManager.MAIN_PATH);
        } catch (LogicException e) {
            LOG.error("Exception during matches by tournament search",e);
            page = redirectToErrorPage(sessionRequestContent, e);
        }
        return page;
    }
}

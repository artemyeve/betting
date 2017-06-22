package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 06.06.2017.
 */
public class MainCommand extends AbstractCommand {
    private final String MATCH_LIST_ATTR = "match_list";
    private final String IS_DELETED = "is_deleted";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        MatchLogic matchLogic=new MatchLogic();
        List<Match> matchList=new ArrayList<>();
        try{
            matchList=matchLogic.findAllMatches();
        }catch (LogicException e){
            LOG.error("Exception during last ordered tracks search",e);
        }
        sessionRequestContent.setSessionAttribute(NUM_PAGE, 0);
        sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, matchList);
        sessionRequestContent.setSessionAttribute(IS_DELETED, false);
        return ConfigurationManager.getProperty(ConfigurationManager.MAIN_PATH);
    }
}

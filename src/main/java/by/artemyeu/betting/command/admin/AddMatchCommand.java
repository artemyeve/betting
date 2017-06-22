package by.artemyeu.betting.command.admin;

import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;


/**
 * Created by Acer on 16.05.2017.
 */
public class AddMatchCommand extends AbstractCommand {

    /** The Constant RESULT_ATTR. */
    private static final String RESULT_ATTR = "result";

      /** The tournament param. */
    private final String TOURNAMENT_PARAM = "tournament";

    /** The home team param. */
    private final String HOME_TEAM_PARAM = "homeTeam";

    /** The away team param. */
    private final String AWAY_TEAM_PARAM = "awayTeam";

    /** The home team goals param. */
    private final String HOME_TEAM_GOALS_PARAM = "homeTeamGoals";

    /** The away team goals param. */
    private final String AWAY_TEAM_GOALS_PARAM = "awayTeamGoals";

    /** The match date param. */
    private final String MATCH_DATE_PARAM = "matchDate";

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
            boolean result = Boolean.valueOf(sessionRequestContent.getRequestAttribute(RESULT_ATTR).toString());
            if (result) {
                MatchLogic matchLogic = new MatchLogic();
                String tournament = sessionRequestContent.getRequestParameter(TOURNAMENT_PARAM);
                String homeTeam = sessionRequestContent.getRequestParameter(HOME_TEAM_PARAM);
                String awayTeam = sessionRequestContent.getRequestParameter(AWAY_TEAM_PARAM);
                String homeTeamGoals = sessionRequestContent.getRequestParameter(HOME_TEAM_GOALS_PARAM);
                String awayTeamGoals = sessionRequestContent.getRequestParameter(AWAY_TEAM_GOALS_PARAM);
                String matchDate = sessionRequestContent.getRequestParameter(MATCH_DATE_PARAM);

                try {
                    String res = matchLogic.addMatch(tournament,homeTeam,awayTeam,homeTeamGoals,awayTeamGoals,matchDate);
                    if (SUCCESS.equals(res)) {

                        sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.ADD_MATCH_SUCCESS));
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    }
                } catch (LogicException e) {
                    LOG.error("Exception during match addition command", e);
                    sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_MATCH_ERROR));
                    page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                }
            } else {
                sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_MATCH_ERROR));
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

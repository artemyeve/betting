package by.artemyeu.betting.command.admin;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.MatchLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.manager.Messenger;
import by.artemyeu.betting.servlet.SessionRequestContent;


import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
public class EditMatchCommand extends AbstractCommand {

    /** The track attr. */
    private final String MATCH_ATTR = "match";

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
            Match match = (Match) sessionRequestContent.getSessionAttribute(MATCH_ATTR);
            MatchLogic matchLogic = new MatchLogic();
            String tournament = sessionRequestContent.getRequestParameter(TOURNAMENT_PARAM);
            String homeTeam = sessionRequestContent.getRequestParameter(HOME_TEAM_PARAM);
            String awayTeam = sessionRequestContent.getRequestParameter(AWAY_TEAM_PARAM);
            String homeTeamGoals = sessionRequestContent.getRequestParameter(HOME_TEAM_GOALS_PARAM);
            String awayTeamGoals = sessionRequestContent.getRequestParameter(AWAY_TEAM_GOALS_PARAM);
            String matchDate = sessionRequestContent.getRequestParameter(MATCH_DATE_PARAM);
            String res;
            try {
                if (!tournament.equals(match.getTournament())) {
                    res = matchLogic.changeTournament(match.getMatchId(), tournament);
                    if (SUCCESS.equals(res)) {
                        match.setTournament(tournament);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                    }
                }
                if (!homeTeam.equals(match.getHomeTeam())) {
                    res = matchLogic.changeHomeTeam(match.getMatchId(), homeTeam);
                    if (SUCCESS.equals(res)) {
                        match.setHomeTeam(homeTeam);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                    }
                }
                if (!awayTeam.equals(match.getAwayTeam())) {
                    res = matchLogic.changeAwayTeam(match.getMatchId(), awayTeam);
                    if (SUCCESS.equals(res)) {
                        match.setAwayTeam(awayTeam);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                    }
                }

                    res = matchLogic.changeHomeTeamGoals(match.getMatchId(), Integer.valueOf(homeTeamGoals));
                    if (SUCCESS.equals(res)) {
                        match.setHomeTeamGoals(Integer.valueOf(homeTeamGoals));
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                    }

                res = matchLogic.changeAwayTeamGoals(match.getMatchId(), Integer.valueOf(awayTeamGoals));
                if (SUCCESS.equals(res)) {
                    match.setAwayTeamGoals(Integer.valueOf(awayTeamGoals));
                } else {
                    sessionRequestContent.setRequestAttribute(ERROR, res);
                    return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                }
                res = matchLogic.changeMatchDate(match.getMatchId(), matchDate);
                if (SUCCESS.equals(res)) {
                    match.setMatchDate(matchDate);
                } else {
                    sessionRequestContent.setRequestAttribute(ERROR, res);
                    return ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
                }

                sessionRequestContent.setSessionAttribute(MATCH_ATTR, match);
                sessionRequestContent.setRequestAttribute(SUCCESS, Messenger.messageManager.getProperty(MessageManager.CHANGE_SUCCESS));
                page = ConfigurationManager.getProperty(ConfigurationManager.MATCH_EDIT_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during change command", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }

        return page;
    }
}

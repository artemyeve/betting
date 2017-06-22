package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
@SuppressWarnings("Duplicates")
public class MatchDAO extends AbstractDAO {

    /** The Constant SQL_ADD_MATCH. */
    private static final String SQL_ADD_MATCH = "INSERT INTO match (tournament,home_team,away_team,home_team_goals,away_team_goals,match_date) VALUES (?,?,?,?,?,?)";

    /** The Constant SQL_CHANGE_TOURNAMENT. */
    private static final String SQL_CHANGE_TOURNAMENT = "UPDATE match SET tournament=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_HOME_TEAM. */
    private static final String SQL_CHANGE_HOME_TEAM = "UPDATE match SET home_team=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_AWAY_TEAM. */
    private static final String SQL_CHANGE_AWAY_TEAM = "UPDATE match SET away_team=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_HOME_TEAM_GOALS. */
    private static final String SQL_CHANGE_HOME_TEAM_GOALS = "UPDATE match SET home_team_goals=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_AWAY_TEAM_GOALS. */
    private static final String SQL_CHANGE_AWAY_TEAM_GOALS = "UPDATE match SET away_team_goals=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_MATCH_DATE. */
    private static final String SQL_CHANGE_MATCH_DATE = "UPDATE match SET match_date=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_ACTIVE_STATUS. */
    private static final String SQL_CHANGE_ACTIVE_STATUS = "UPDATE match SET active=? WHERE match_id=?";

    /** The Constant SQL_DELETE_MATCH. */
    private static final String SQL_DELETE_MATCH = "DELETE FROM match WHERE match_id = ?";

    /** The Constant SQL_FIND_MATCH_BY_ID. */
    private static final String SQL_FIND_MATCH_BY_ID = "SELECT match.tournament,match.home_team,\n" +
            "match.away_team, match.home_team_goals,match.away_team_goals,match.match_date,match.active FROM match\n" +
            "WHERE match.id=?";

    /** The Constant SQL_FIND_MATCH_BY_TOURNAMENT. */
    private static final String SQL_FIND_MATCH_BY_TOURNAMENT = "SELECT match.home_team,match.away_team,\n" +
            "match.home_team_goals,match.away_team_goals,match.match_date,match.active FROM match\n" +
            "WHERE match.tournament=?";

    /** The Constant SQL_FIND_MATCH_BY_MATCH_DATE. */
    private static final String SQL_FIND_MATCH_BY_MATCH_DATE = "SELECT match.tournament,match.home_team,\n" +
            "match.away_team,match.home_team_goals,match.away_team_goals,match.match_date,match.active FROM match\n" +
            "WHERE match.match_date=?";

    /** The Constant SQL_FIND_ALL_MATCHES. */
    private static final String SQL_FIND_ALL_MATCHES = "SELECT match.match_id,match.tournament,match.home_team,\n" +
            "match.away_team, match.home_team_goals,match.away_team_goals,match.match_date,match.active FROM match\n" +
            "ORDER BY match.match_date";

    /** The Constant SQL_FIND_ALL_ACTIVE_MATCHES. */
    private static final String SQL_FIND_ALL_ACTIVE_MATCHES = "SELECT match.match_id,match.tournament,match.home_team,\n" +
            "match.away_team, match.home_team_goals,match.away_team_goals,match.match_date,match.active FROM match\n" +
            "WHERE match.active = 0 ORDER BY match.match_date";

    /**
     * Instantiates a new match DAO.
     *
     * @param connection the connection
     */
    public MatchDAO(ProxyConnection connection) {
        super(connection);
    }

    /**
     * Adds the match.
     * @param tournament the tournament
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @param homeTeamGoals the home team goals
     * @param awayTeamGoals the away team goals
     * @param matchDate the match date
     * @throws DAOException the DAO exception
     */
    public void addMatch(String tournament, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals,
                         Date matchDate) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_MATCH);
            statement.setString(1, tournament);
            statement.setString(2, homeTeam);
            statement.setString(3, awayTeam);
            statement.setInt(4, homeTeamGoals);
            statement.setInt(5, awayTeamGoals);
            statement.setDate(6, new java.sql.Date(matchDate.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during match addition", e);
        } finally {
            closeStatement(statement);
        }
    }


    /**
     * Delete match by id.
     *
     * @param id the id
     * @throws DAOException the DAO exception
     */
    public void deleteMatchById(int id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_MATCH);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during match removal", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Find all matches.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Match> findAllMatches() throws DAOException {
        List<Match> matchList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_MATCHES);
            ResultSet set = statement.executeQuery();
            matchList = formMatchList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during all matches search",e);
        } finally {
            closeStatement(statement);
        }
        return matchList;
    }

    /**
     * Find match by id.
     *
     * @param id the id
     * @return the match
     * @throws DAOException the DAO exception
     */
    public Match findMatchById(int id) throws DAOException {
        Match match;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_MATCH_BY_ID);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            match = formMatchList(set).get(0);
        } catch (SQLException e) {
            throw new DAOException("Exception during match by id search", e);
        } finally {
            closeStatement(statement);
        }
        return match;
    }



    /**
     * Form match list.
     *
     * @param set the set
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Match> formMatchList(ResultSet set) throws DAOException {
        List<Match> matchList = new ArrayList<>();
        try {
            while (set.next()) {
                int matchId = set.getInt("matchId");
                String tournament = set.getString("tournament");
                String homeTeam = set.getString("homeTeam");
                String awayTeam = set.getString("awayTeam");
                int homeTeamGoals = set.getInt("homeTeamGoals");
                int awayTeamGoals = set.getInt("awayTeamGoals");
                Date matchDate = set.getDate("matchDate");
                boolean active = set.getBoolean("active");
                matchList.add(new Match(matchId, tournament,homeTeam,awayTeam,homeTeamGoals,awayTeamGoals,matchDate,active));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during match list formation ", e);
        }
        return matchList;
    }
}

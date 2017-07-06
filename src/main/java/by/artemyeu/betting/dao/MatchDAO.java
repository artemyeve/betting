package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
@SuppressWarnings("Duplicates")
public class MatchDAO extends AbstractDAO {

    /** The Constant SQL_ADD_MATCH. */
    private static final String SQL_ADD_MATCH = "INSERT INTO bets.match (tournament,home_team,away_team,home_team_goals,away_team_goals,match_date) VALUES (?,?,?,?,?,?)";

    /** The Constant SQL_CHANGE_TOURNAMENT. */
    private static final String SQL_CHANGE_TOURNAMENT = "UPDATE bets.match SET tournament=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_HOME_TEAM. */
    private static final String SQL_CHANGE_HOME_TEAM = "UPDATE bets.match SET home_team=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_AWAY_TEAM. */
    private static final String SQL_CHANGE_AWAY_TEAM = "UPDATE bets.match SET away_team=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_HOME_TEAM_GOALS. */
    private static final String SQL_CHANGE_HOME_TEAM_GOALS = "UPDATE bets.match SET home_team_goals=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_AWAY_TEAM_GOALS. */
    private static final String SQL_CHANGE_AWAY_TEAM_GOALS = "UPDATE bets.match SET away_team_goals=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_MATCH_DATE. */
    private static final String SQL_CHANGE_MATCH_DATE = "UPDATE bets.match SET match_date=? WHERE match_id=?";

    /** The Constant SQL_CHANGE_MATCH_STATUS. */
    private static final String SQL_CHANGE_MATCH_STATUS = "UPDATE bets.match SET active=? WHERE match_id=?";

    /** The Constant SQL_DELETE_MATCH. */
    private static final String SQL_DELETE_MATCH = "DELETE FROM bets.match WHERE match_id = ?";

    /** The Constant SQL_FIND_MATCH_BY_ID. */
    private static final String SQL_FIND_MATCH_BY_ID = "SELECT tournament,home_team,away_team, home_team_goals,away_team_goals,match_date,active FROM bets.match WHERE match_id=?";

    /** The Constant SQL_FIND_MATCH_BY_TOURNAMENT. */
    private static final String SQL_FIND_MATCH_BY_TOURNAMENT = "SELECT home_team,away_team,home_team_goals,away_team_goals,match_date,active FROM bets.match WHERE tournament=?";

    /** The Constant SQL_FIND_MATCH_BY_MATCH_DATE. */
    private static final String SQL_FIND_MATCH_BY_MATCH_DATE = "SELECT tournament,home_team,away_team,home_team_goals,away_team_goals,match_date,active FROM bets.match WHERE match_date=?";

    /** The Constant SQL_FIND_ALL_MATCHES. */
    private static final String SQL_FIND_ALL_MATCHES = "SELECT match_id,tournament,home_team,away_team, home_team_goals,away_team_goals,match_date,active FROM bets.match";

    /** The Constant SQL_FIND_ALL_ACTIVE_MATCHES. */
    private static final String SQL_FIND_ALL_ACTIVE_MATCHES = "SELECT match_id,tournament,home_team,away_team, home_team_goals,away_team_goals,match_date,active FROM bets.match WHERE active = true ORDER BY match_date";

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
     * Find all active matches.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Match> findAllActiveMatches() throws DAOException {
        List<Match> matchList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_MATCHES);
            ResultSet set = statement.executeQuery();
            matchList = formMatchList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during all active matches search",e);
        } finally {
            closeStatement(statement);
        }
        return matchList;
    }

    /**
     * Change tournament.
     *
     * @param matchId the match id
     * @param newTournament the new tournament
     * @throws DAOException the DAO exception
     */
    public void changeTournament(int matchId, String newTournament) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_TOURNAMENT);
            statement.setString(1, newTournament);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing tournament", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change home team.
     *
     * @param matchId the match id
     * @param newHomeTeam the new home team
     * @throws DAOException the DAO exception
     */
    public void changeHomeTeam(int matchId, String newHomeTeam) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_HOME_TEAM);
            statement.setString(1, newHomeTeam);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing home team", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change away team.
     *
     * @param matchId the match id
     * @param newAwayTeam the new away team
     * @throws DAOException the DAO exception
     */
    public void changeAwayTeam(int matchId, String newAwayTeam) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_AWAY_TEAM);
            statement.setString(1, newAwayTeam);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing away team", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change home team goals.
     *
     * @param matchId the match id
     * @param newHomeTeamGoals the new home team goals
     * @throws DAOException the DAO exception
     */
    public void changeHomeTeamGoals(int matchId, int newHomeTeamGoals) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_HOME_TEAM_GOALS);
            statement.setInt(1, newHomeTeamGoals);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing home team goals", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change away team goals.
     *
     * @param matchId the match id
     * @param newAwayTeamGoals the new away team goals
     * @throws DAOException the DAO exception
     */
    public void changeAwayTeamGoals(int matchId, int newAwayTeamGoals) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_AWAY_TEAM_GOALS);
            statement.setInt(1, newAwayTeamGoals);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing away team goals", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change match date.
     *
     * @param matchId the match id
     * @param newMatchDate the new match date
     * @throws DAOException the DAO exception
     */
    public void changeMatchDate(int matchId, Date newMatchDate) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_MATCH_DATE);
            statement.setDate(1, new java.sql.Date(newMatchDate.getTime()));
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing match date", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change match status.
     *
     * @param matchId the match id
     * @param newMatchStatus the new match status
     * @throws DAOException the DAO exception
     */
    public void changeMatchStatus(int matchId, boolean newMatchStatus) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_MATCH_STATUS);
            statement.setBoolean(1, newMatchStatus);
            statement.setInt(2, matchId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing match status", e);
        } finally {
            closeStatement(statement);
        }
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
            throw new DAOException("Exception during find match by id", e);
        } finally {
            closeStatement(statement);
        }
        return match;
    }

    /**
     * Find matches by tournament.
     *
     * @param tournament the tournament
     * @return the match
     * @throws DAOException the DAO exception
     */
    public List<Match> findMatchesByTournament(String tournament) throws DAOException {
        List<Match> matchList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_MATCH_BY_TOURNAMENT);
            statement.setString(1, tournament);
            ResultSet set = statement.executeQuery();
            matchList = formMatchList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during find match by tournament", e);
        } finally {
            closeStatement(statement);
        }
        return matchList;
    }
    /**
     * Find matches by date.
     *
     * @param matchDate the match date
     * @return the match
     * @throws DAOException the DAO exception
     */
    public List<Match> findMatchesByDate(Date matchDate) throws DAOException {
        List<Match> matchList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_MATCH_BY_MATCH_DATE);
            statement.setDate(1, new java.sql.Date(matchDate.getTime()));
            ResultSet set = statement.executeQuery();
            matchList = formMatchList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during find match by date", e);
        } finally {
            closeStatement(statement);
        }
        return matchList;
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
                int matchId = set.getInt("match_id");
                String tournament = set.getString("tournament");
                String homeTeam = set.getString("home_team");
                String awayTeam = set.getString("away_team");
                int homeTeamGoals = set.getInt("home_team_goals");
                int awayTeamGoals = set.getInt("away_team_goals");

                Date matchDate = set.getTimestamp("match_date");
                boolean active = set.getBoolean("active");
                matchList.add(new Match(matchId, tournament,homeTeam,awayTeam,homeTeamGoals,awayTeamGoals,matchDate,active));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during match list formation ", e);
        }
        return matchList;
    }




}

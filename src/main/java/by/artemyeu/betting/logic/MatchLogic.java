package by.artemyeu.betting.logic;

import by.artemyeu.betting.dao.MatchDAO;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.Messenger;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Acer on 16.05.2017.
 */
public class MatchLogic implements Messenger {

    /**
     * The success.
     */
    private final String SUCCESS = "success";

    /**
     * Adds the match.
     *
     * @param tournament    the tournament
     * @param homeTeam      the home team
     * @param awayTeam      the away team
     * @param homeTeamGoals the home team goals
     * @param awayTeamGoals the away team goals
     * @param matchDate     the match date
     * @throws LogicException the logic exception
     */
    public String addMatch(String tournament, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals,
                           Date matchDate) throws LogicException {

        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.addMatch(tournament, homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchDate);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Exception during match addition", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }


    /**
     * Change tournament.
     *
     * @param matchId the match id
     * @param newTournament the new name
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeTournament(int matchId, String newTournament) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            MatchDAO matchDAO = new MatchDAO(connection);
            try {
                matchDAO.changeTournament(matchId, newTournament);
                return SUCCESS;
            } catch (DAOException e) {
                throw new LogicException("Error during changing match name", e);
            } finally {
                matchDAO.closeConnection(connection);
            }

    }

    /**
     * Change home team.
     *
     * @param matchId the match id
     * @param newHomeTeam the new home team
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeHomeTeam(int matchId, String newHomeTeam) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.changeHomeTeam(matchId, newHomeTeam);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Error during changing home team", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }

    /**
     * Change away team.
     *
     * @param matchId the match id
     * @param newAwayTeam the new away team
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeAwayTeam(int matchId, String newAwayTeam) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.changeAwayTeam(matchId, newAwayTeam);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Error during changing away team", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }

    /**
     * Change home team goals.
     *
     * @param matchId the match id
     * @param newHomeTeamGoals the new home team goals
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeHomeTeamGoals(int matchId, int newHomeTeamGoals) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.changeHomeTeamGoals(matchId, newHomeTeamGoals);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Error during changing home team goals", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }
    /**
     * Change home team goals.
     *
     * @param matchId the match id
     * @param newAwayTeamGoals the new away team goals
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeAwayTeamGoals(int matchId, int newAwayTeamGoals) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.changeAwayTeamGoals(matchId, newAwayTeamGoals);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Error during changing away team goals", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }

    /**
     * Change home team goals.
     *
     * @param matchId the match id
     * @param newMatchDate the new match date
     * @return the string
     * @throws LogicException the logic exception
     */
    public String changeMatchDate(int matchId, Date newMatchDate) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            matchDAO.changeMatchDate(matchId, newMatchDate);
            return SUCCESS;
        } catch (DAOException e) {
            throw new LogicException("Error during changing match date", e);
        } finally {
            matchDAO.closeConnection(connection);
        }

    }

    /**
     * Delete match by id.
     *
     * @param id the id
     * @throws LogicException the logic exception
     */
    public void deleteMatchById(int id) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO trackDAO = new MatchDAO(connection);
        try {
            trackDAO.deleteMatchById(id);
        } catch (DAOException e) {
            throw new LogicException("Exception during match removal", e);
        } finally {
            trackDAO.closeConnection(connection);
        }
    }

    /**
     * Find all matches.
     *
     * @return the list
     * @throws LogicException the logic exception
     */
    public List<Match> findAllMatches() throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            return matchDAO.findAllMatches();
        } catch (DAOException e) {
            throw new LogicException("Exception during all matches search", e);
        } finally {
            matchDAO.closeConnection(connection);
        }
    }

    /**
     * Find all active matches.
     *
     * @return the list
     * @throws LogicException the logic exception
     */
    public List<Match> findAllActiveMatches() throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            List<Match> allMatches = matchDAO.findAllActiveMatches();
            List<Match> res = new ArrayList<>();
            for (Match temp : allMatches) {
                if (temp.isActive()) {
                    res.add(temp);
                }
            }
            return res;
        } catch (DAOException e) {
            throw new LogicException("Exception during matches search", e);
        } finally {
            matchDAO.closeConnection(connection);
        }
    }


    /**
     * Find match by id.
     *
     * @param id the id
     * @return the match
     * @throws LogicException the logic exception
     */
    public Match findMatchById(int id) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            return matchDAO.findMatchById(id);
        } catch (DAOException e) {
            throw new LogicException("Exception during match by id search", e);
        } finally {
            matchDAO.closeConnection(connection);
        }
    }
    /**
     * Find matches by date.
     *
     * @param matchDate the match date
     * @return the match
     * @throws LogicException the logic exception
     */
    public List<Match> findMatchByDate(Date matchDate) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            return matchDAO.findMatchesByDate(matchDate);
        } catch (DAOException e) {
            throw new LogicException("Exception during match by date search", e);
        } finally {
            matchDAO.closeConnection(connection);
        }
    }
    /**
     * Find matches by tournament.
     *
     * @param tournament the tournament
     * @return the match
     * @throws LogicException the logic exception
     */
    public List<Match> findMatchByTournament(String tournament) throws LogicException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        MatchDAO matchDAO = new MatchDAO(connection);
        try {
            return matchDAO.findMatchesByTournament(tournament);
        } catch (DAOException e) {
            throw new LogicException("Exception during match by tournament search", e);
        } finally {
            matchDAO.closeConnection(connection);
        }
    }

}

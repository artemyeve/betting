package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.pool.ProxyConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 01.06.2017.
 */
public class BetLineDAO extends AbstractDAO {

    /** The Constant SQL_ADD_ODD_TO_OUTCOME. */
    private static final String SQL_ADD_OUTCOME_TO_BET_LINE = "INSERT INTO bet_line (match_id,outcome,odd) VALUES (?,?,?)";

    /** The Constant SQL_CHANGE_OUTCOME. */
    private static final String SQL_CHANGE_OUTCOME = "UPDATE bet_line SET outcome=? WHERE bet_line_id=?";

    /** The Constant SQL_CHANGE_ODD. */
    private static final String SQL_CHANGE_ODD = "UPDATE bet_line SET outcome=? WHERE bet_line_id=?";

    /** The Constant SQL_DELETE_OUTCOME_FROM_BET_LINE. */
    private static final String SQL_DELETE_OUTCOME_FROM_BET_LINE = "DELETE FROM bet_line WHERE bet_line_id = ?";

    /** The Constant SQL_FIND_ODD_BY_BET_LINE_ID. */
    private static final String SQL_FIND_ODD_BY_BET_LINE_ID = "SELECT bet_line.odd\n" +
            "FROM bet_line\n" +
            "WHERE bet_line.bet_line_id=?";

       /** The Constant SQL_FIND_MATCH_BY_BET_LINE_ID. */
    private static final String SQL_FIND_MATCH_BY_BET_LINE_ID = "SELECT bet_line.match_id\n" +
               "FROM bet_line\n" +
               "WHERE bet_line.bet_line_id=?";

    /** The Constant SQL_FIND_OUTCOME_BY_BET_LINE_ID. */
    private static final String SQL_FIND_OUTCOME_BY_BET_LINE_ID = "SELECT bet_line.outcome\n" +
            "FROM bet_line\n" +
            "WHERE bet_line.bet_line_id=?";

    /**
     * Instantiates a new bet line DAO.
     *
     * @param connection the connection
     */
    public BetLineDAO(ProxyConnection connection) {
        super(connection);
    }

    /**
     * Adds outcome to bet line.
     * @param matchId the match id
     * @param outcome the outcome
     * @param odd the odd
     * @throws DAOException the DAO exception
     */
    public void addOutcome(int matchId, String outcome,double odd) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_OUTCOME_TO_BET_LINE);
            statement.setInt(1, matchId);
            statement.setString(2, outcome);
            statement.setDouble(3,odd);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during match addition", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change odd.
     *
     * @param betLineId the betLineId
     * @param odd the odd
     * @throws DAOException the DAO exception
     */
    public void changeOdd(int betLineId, double odd) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_ODD);
            statement.setInt(1, betLineId);
            statement.setDouble(1, odd);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during odd change", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Change outcome.
     *
     * @param betLineId the betLineId
     * @param outcome the outcome
     * @throws DAOException the DAO exception
     */
    public void changeOutcome(int betLineId, String outcome) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_OUTCOME);
            statement.setInt(1, betLineId);
            statement.setString(1, outcome);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during outcome change", e);
        } finally {
            closeStatement(statement);
        }
    }


    /**
     * Delete outcome by id.
     *
     * @param betLineId the bet line id
     * @throws DAOException the DAO exception
     */
    public void deleteOutcome(int betLineId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_OUTCOME_FROM_BET_LINE);
            statement.setInt(1, betLineId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during outcome removal", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Find odd by bet line id.
     *
     * @param betLineId the bet line id
     * @throws DAOException the DAO exception
     */
    public void findOddByBetLineId(int betLineId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ODD_BY_BET_LINE_ID);
            statement.setInt(1, betLineId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during search odd by bet line id", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Find match by bet line id.
     *
     * @param betLineId the bet line id
     * @throws DAOException the DAO exception
     */
    public void findMatchByBetLineId(int betLineId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_MATCH_BY_BET_LINE_ID);
            statement.setInt(1, betLineId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during search match by bet line id", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Find outcome by bet line id.
     *
     * @param betLineId the bet line id
     * @throws DAOException the DAO exception
     */
    public void findOutcomeByBetLineId(int betLineId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_OUTCOME_BY_BET_LINE_ID);
            statement.setInt(1, betLineId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during search outcome by bet line id", e);
        } finally {
            closeStatement(statement);
        }
    }



}

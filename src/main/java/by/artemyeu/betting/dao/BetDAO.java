package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Bet;
import by.artemyeu.betting.entity.Match;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.pool.ProxyConnection;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
@SuppressWarnings("Duplicates")
public class BetDAO extends AbstractDAO {

    /** The date pattern. */
    private final String DATE_PATTERN="yyyy-MM-dd HH:mm:ss";

    /** The Constant SQL_ADD_BET. */
    private static final String SQL_ADD_BET = "INSERT INTO bet(bet_amount,bet_date,user_id) VALUES(?,?,?);";
    /** The Constant SQL_CHANGE_BET_AMOUNT. */
    private static final String SQL_CHANGE_BET_AMOUNT = "UPDATE bet SET bet_amount = ? WHERE bet_id = ?";
    /** The Constant SQL_CHANGE_BET_STATUS. */
    private static final String SQL_CHANGE_BET_STATUS = "UPDATE bet SET active = ? WHERE bet_id = ?";
    /** The Constant SQL_DELETE_BET. */
    private static final String SQL_DELETE_BET = "DELETE FROM bet WHERE bet_id = ?";

    /** The Constant SQL_FIND_ALL_ACTIVE_USER_BETS. */
    private static final String SQL_FIND_ALL_ACTIVE_USER_BETS = "SELECT bet.id, bet_amount,bet_date\n" +
            "FROM `bet`\n" +
            "WHERE bet.user_id=? and bet.active = true\n" +
            "ORDER BY bet.bet_date";

    /** The Constant SQL_FIND_ALL_USER_BETS. */
    private static final String SQL_FIND_ALL_USER_BETS = "SELECT bet.id, bet_amount,bet_date\n" +
            "FROM `bet`\n" +
            "WHERE bet.user_id=?\n" +
            "ORDER BY bet.bet_date";


    /**
     * Instantiates a new bet DAO.
     *
     * @param connection the connection
     */
    public BetDAO(ProxyConnection connection) {
        super(connection);
    }

    /**
     * Adds the bet.
     * @param betAmount the bet amount
     * @param userId the user id
     * @throws DAOException the DAO exception
     */
    public void addBet(BigDecimal betAmount,int userId) throws DAOException {
        PreparedStatement statement = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            statement = connection.prepareStatement(SQL_ADD_BET);
            statement.setBigDecimal(1, betAmount);
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during bet addition",e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change bet amount.
     *
     * @param betId the bet id
     * @param newBetAmount the new bet amount
     * @throws DAOException the DAO exception
     */
    public void changeBetAmount(int betId, BigDecimal newBetAmount) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_BET_AMOUNT);
            statement.setBigDecimal(1, newBetAmount);
            statement.setInt(2, betId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing bet amount", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Change bet status.
     *
     * @param betId the bet id
     * @param newBetStatus the new bet status
     * @throws DAOException the DAO exception
     */
    public void changeBetStatus(int betId, boolean newBetStatus) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CHANGE_BET_STATUS);
            statement.setBoolean(1, newBetStatus);
            statement.setInt(2, betId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during changing bet status", e);
        } finally {
            closeStatement(statement);
        }
    }

    /**
     * Delete bet by id.
     *
     * @param id the id
     * @throws DAOException the DAO exception
     */
    public void deleteBetById(int id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BET);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during bet removal", e);
        } finally {
            closeStatement(statement);
        }
    }
    /**
     * Find all active users bets.
     ** @param userId the user id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Bet> findAllActiveUserBets(int userId) throws DAOException {
        List<Bet> betList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_USER_BETS);
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();
            betList = formBetList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during all active users bets search",e);
        } finally {
            closeStatement(statement);
        }
        return betList;
    }
    /**
     * Find all users bets.
     ** @param userId the user id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Bet> findAllUserBets(int userId) throws DAOException {
        List<Bet> betList;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ALL_USER_BETS);
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();
            betList = formBetList(set);
        } catch (SQLException e) {
            throw new DAOException("Exception during all user bets search",e);
        } finally {
            closeStatement(statement);
        }
        return betList;
    }
    /**
     * Form bet list.
     *
     * @param set the set
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<Bet> formBetList(ResultSet set) throws DAOException {
        List<Bet> betList = new ArrayList<>();
        try {
            while (set.next()) {
                int betId = set.getInt("betId");
                BigDecimal betAmount = set.getBigDecimal("netAmount");
                Date betDate = set.getDate("betDate");
                boolean active = set.getBoolean("active");
                int userId = set.getInt("userId");
                betList.add(new Bet(betId, betAmount,betDate,active,userId));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during match list formation ", e);
        }
        return betList;
    }


}


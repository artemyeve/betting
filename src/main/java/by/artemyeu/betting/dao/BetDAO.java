package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Bet;
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
public class BetDAO extends AbstractDAO {

    /** The date pattern. */
    private final String DATE_PATTERN="yyyy-MM-dd HH:mm:ss";

    /** The Constant SQL_ADD_BET. */
    private static final String SQL_ADD_BET = "INSERT INTO bet(bet_amount,bet_date,user_id) VALUES(?,?,?);";
    /** The Constant SQL_UPDATE_BET. */
    private static final String UPDATE_BET = "UPDATE bet SET bet_amount = ? , bet_date=? , user_id = ? WHERE bet_id = ?";
    /** The Constant SQL_DELETE_BET. */
    private static final String DELETE_BET = "DELETE FROM bet WHERE bet_id = ?";

    /** The Constant SQL_FIND_ACTIVE_USER_BETS. */
    private static final String SQL_FIND_ACTIVE_USER_BETS = "SELECT bet.id, bet_amount,bet_date\n" +
            "FROM `bet`\n" +
            "WHERE bet.user_id=? and bet.active = 1\n" +
            "ORDER BY bet.bet_date";

    /** The Constant SQL_FIND_INACTIVE_USER_BETS. */
    private static final String SQL_FIND_INACTIVE_USER_BETS = "SELECT bet.id, bet_amount,bet_date\n" +
            "FROM `bet`\n" +
            "WHERE bet.user_id=? and bet.active = 0\n" +
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
     * @param betDate the bet date
     * @param userId the user id
     * @throws DAOException the DAO exception
     */
    public void addBet(BigDecimal betAmount,Date betDate, int userId) throws DAOException {
        PreparedStatement statement = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime now=LocalDateTime.now();
            String dateTime = now.format(formatter);
            statement = connection.prepareStatement(SQL_ADD_BET);
            statement.setBigDecimal(1, betAmount);
            statement.setDate(2, new java.sql.Date(betDate.getTime()));
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception during bet addition",e);
        } finally {
            closeStatement(statement);
        }
    }

}


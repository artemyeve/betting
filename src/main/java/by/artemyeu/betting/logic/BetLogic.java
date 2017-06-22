package by.artemyeu.betting.logic;

import by.artemyeu.betting.dao.BetDAO;
import by.artemyeu.betting.entity.Account;
import by.artemyeu.betting.entity.Bet;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;
import by.artemyeu.betting.dao.UserDAO;
import by.artemyeu.betting.entity.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 01.06.2017.
 */
public class BetLogic {

    /**
     * Adds the bet.
     *
     * @param betAmount the bet amount
     * @param betDate the bet date
     * @param user the bet date
     * @param account the account
     * @throws LogicException the logic exception
     */
    public void addBet(BigDecimal betAmount, Date betDate, User user, Account account) throws LogicException {

        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        BetDAO betDAO = new BetDAO(connection);
        UserDAO userDAO = new UserDAO(connection);
        try {
            try {
                connection.setAutoCommit(false);
                BigDecimal money = account.getBalance();
                userDAO.changeCash(money.subtract(betAmount),account.getId());
                betDAO.addBet(betAmount, betDate,user.getId());
                connection.commit();
            } catch (SQLException e) {
                throw new LogicException("Exception during bet addition", e);
            } catch (DAOException e) {
                connection.rollback();
                throw new LogicException("Exception during bet addition", e);
            }finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new LogicException("Exception during bet addition", e);
        }finally {
            betDAO.closeConnection(connection);
        }
    }

}

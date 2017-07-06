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
     * @param user the user
     * @param betAmount the bet amount
     * @throws LogicException the logic exception
     */
    public void addBet( User user, String betAmount) throws LogicException {

        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        BetDAO betDAO = new BetDAO(connection);
        UserDAO userDAO = new UserDAO(connection);
        try {
            try {
                connection.setAutoCommit(false);
                BigDecimal amount = new BigDecimal(betAmount);
                BigDecimal balance = user.getBalance();
                userDAO.changeCash(balance.subtract(amount),user.getId());
                betDAO.addBet(amount, user.getId());
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

package by.artemyeu.betting.logic;

import by.artemyeu.betting.dao.BetLineDAO;
import by.artemyeu.betting.dao.MatchDAO;
import by.artemyeu.betting.exception.DAOException;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Acer on 01.06.2017.
 */
public class BetLineLogic {

    /**
     * The success.
     */
    private final String SUCCESS = "success";


    /**
     * Adds the outcome.
     *
     * @param matchId     the matchId
     * @param outcome     the outcome
     * @param odd         the odd
     * @throws LogicException the logic exception
     */
    public String addOutcome(String matchId, String outcome, String odd) throws LogicException {

        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        BetLineDAO betLineDAO = new BetLineDAO(connection);
        try {
            int newMatchId = Integer.valueOf(matchId);
            double newOdd = Double.valueOf(odd);
            betLineDAO.addOutcome(newMatchId,outcome,newOdd);
            return SUCCESS;
        } catch ( DAOException e) {
            throw new LogicException("Exception during outcome addition", e);
        } finally {
            betLineDAO.closeConnection(connection);
        }

    }
    /**
     * Delete the outcome.
     *
     * @param betLineId     the bet line Id
     * @throws LogicException the logic exception
     */

    public String deleteOutcome(int betLineId) throws LogicException {

        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        BetLineDAO betLineDAO = new BetLineDAO(connection);
        try {
            betLineDAO.deleteOutcome(betLineId);
            return SUCCESS;
        } catch ( DAOException e) {
            throw new LogicException("Exception during outcome addition", e);
        } finally {
            betLineDAO.closeConnection(connection);
        }

    }
}

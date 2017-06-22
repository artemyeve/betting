package by.artemyeu.betting.dao;

import by.artemyeu.betting.entity.Entity;
import by.artemyeu.betting.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Acer on 16.05.2017.
 */
public abstract class AbstractDAO <T extends Entity> {
    private static final Logger LOG = LogManager.getLogger();
    protected ProxyConnection connection;

    /**
     * Instantiates a new abstract DAO.
     *
     * @param connection the connection
     */
    public AbstractDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            } else {
                LOG.warn("Can't close statement. Statement was'n created");
            }
        } catch (SQLException e) {
            LOG.error("Error during statement closing", e);
        }
    }

    public void closeConnection(ProxyConnection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error("Error during connection to pull return", e);
        }
    }
}

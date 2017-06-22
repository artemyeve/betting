package test.by.artemyeu.betting;

/**
 * Created by Acer on 16.05.2017.
 */
import by.artemyeu.betting.pool.ConnectionPool;
import by.artemyeu.betting.pool.ProxyConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPoolTest {
    private static ConnectionPool pool;
    private static ArrayList<ProxyConnection> connections;

    @BeforeClass
    public static void initConnectionPool() {
        pool = ConnectionPool.getInstance();
    }

    @Before
    public void initConnections() {
        connections = new ArrayList<>();
    }

    @After
    public void destroyConnections() {
        connections.clear();
    }

    @AfterClass
    public static void closeConnectionPool() {
        pool.terminatePool();
    }

    @Test
    public void checkGetConnection() throws SQLException {
        ProxyConnection connection=pool.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void checkReturnConnection() throws SQLException {
        ProxyConnection connectionFirst = pool.getConnection();
        connectionFirst.close();
        ProxyConnection connectionSecond;
        for (int i = 0; i < 9; i++) {
            connectionSecond = pool.getConnection();
            connectionSecond.close();
        }
        connectionSecond = pool.getConnection();
        connectionSecond.close();
        Assert.assertEquals(connectionFirst, connectionSecond);
    }
}

package by.artemyeu.betting.pool;

import by.artemyeu.betting.manager.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Created by Acer on 16.05.2017.
 */
public class InitDB {
    private static final Logger LOG = LogManager.getLogger();
    final String DATABASE_LOGIN;
    final  String DATABASE_PASS;
    final  String DATABASE_URL;
    final  int POOL_SIZE;


    /**
     * Instantiates a new inits the DB.
     */
    InitDB() {
        Properties properties=new Properties();
        String propFileName="database.properties";
        try {
            InputStream inputStream=getClass().getClassLoader().getResourceAsStream(propFileName);
            if(inputStream!=null){
                properties.load(inputStream);
            }else {
                LOG.fatal("Properties was not found");
                throw new RuntimeException("Properties was not found");
            }
        } catch (IOException | MissingResourceException e) {
            LOG.fatal("Exception during database initialization", e);
            throw new RuntimeException("Exception during database initialization", e);
        }
        DATABASE_URL = properties.getProperty(DBManager.DB_URL);
        DATABASE_LOGIN = properties.getProperty(DBManager.DB_USER);
        DATABASE_PASS = properties.getProperty(DBManager.DB_PASSWORD);
        POOL_SIZE = Integer.valueOf(DBManager.getProperty(DBManager.DB_POOL_SIZE));
    }
}

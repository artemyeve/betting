package by.artemyeu.betting.manager;

import java.util.ResourceBundle;


/**
 * Created by Acer on 16.05.2017.
 */
public class DBManager {

    /** The Constant DB_PASSWORD. */
    public static final String DB_PASSWORD = "db.password";

    /** The Constant DB_POOL_SIZE. */
    public static final String DB_POOL_SIZE = "db.poolsize";

    /** The Constant DB_URL. */
    public static final String DB_URL = "db.url";

    /** The Constant DB_USER. */
    public static final String DB_USER = "db.user";

    /** The Constant resourceBundle. */
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

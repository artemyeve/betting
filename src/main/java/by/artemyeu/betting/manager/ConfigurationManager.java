package by.artemyeu.betting.manager;

import java.util.ResourceBundle;

/**
 * Created by Acer on 16.05.2017.
 */
public class ConfigurationManager {

    /** The Constant ADD_MATCH_PATH. */
    public static final String ADD_MATCH_PATH = "path.page.add.match";

    /** The Constant CHANGE_PATH. */
    public static final String CHANGE_PATH = "path.page.change";

    /** The Constant CHANGE_PASS_PATH. */
    public static final String CHANGE_PASS_PATH = "path.page.pass";

    /** The Constant ERROR_PATH. */
    public static final String ERROR_PATH = "path.page.error";

    /** The Constant HOME_PATH. */
    public static final String HOME_PATH = "path.page.home";

    /** The Constant LOGIN_PATH. */
    public static final String LOGIN_PATH = "path.page.login";

    /** The Constant MAIN_PATH. */
    public static final String MAIN_PATH = "path.page.main";

    /** The Constant MONEY_PATH. */
    public static final String MONEY_PATH = "path.page.add.funds";

    /** The Constant MY_BETS_PATH. */
    public static final String MY_BETS_PATH = "path.page.my_bets";

    /** The Constant SHOW_MY_BETS_PATH. */
    public static final String SHOW_MY_BETS_PATH = "path.page.bets";

    /** The Constant PROFILE_PATH. */
    public static final String PROFILE_PATH = "path.page.profile";

    /** The Constant SET_BONUS_PATH. */
    public static final String SET_BONUS_PATH = "path.page.bonus";

    /** The Constant SHOW_USERS_PATH. */
    public static final String SHOW_USERS_PATH = "path.page.show_users";

    /** The Constant SIGNUP_PATH. */
    public static final String SIGNUP_PATH = "path.page.signup";

    /** The Constant MATCH_INFO_PATH. */
    public static final String MATCH_INFO_PATH = "path.page.matches_info";

    /** The Constant MATCH_EDIT_PATH. */
    public static final String MATCH_EDIT_PATH = "path.page.match_edit";


    /** The Constant resourceBundle. */
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

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

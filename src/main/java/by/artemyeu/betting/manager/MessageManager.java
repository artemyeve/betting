package by.artemyeu.betting.manager;

import java.util.ResourceBundle;

/**
 * Created by Acer on 01.06.2017.
 */
public class MessageManager {

    /** The Constant ADD_COMMENT_EMPTY. */
    public final static String ADD_COMMENT_EMPTY="message.add.comment.empty";

    /** The Constant ADD_COMMENT_ERROR. */
    public final static String ADD_COMMENT_ERROR="message.add.comment";

    /** The Constant ADD_MATCH_DATA_ERROR. */
    public final static String ADD_MATCH_DATA_ERROR="message.add.match.data.error";

    /** The Constant ADD_MATCH_ERROR. */
    public final static String ADD_MATCH_ERROR="message.add.match.error";

    /** The Constant ADD_MATCH_SUCCESS. */
    public final static String ADD_MATCH_SUCCESS="message.add.match.success";


    /** The Constant CHANGE_CASH_ERROR. */
    public final static String CHANGE_CASH_ERROR="message.change.cash";

    /** The Constant CHANGE_FIRST_NAME_ERROR. */
    public final static String CHANGE_FIRST_NAME_ERROR="message.change.first.name.error";

    /** The Constant CHANGE_SECOND_NAME_ERROR. */
    public final static String CHANGE_SECOND_NAME_ERROR="message.change.second.name.error";

    /** The Constant CHANGE_EMAIL_ERROR. */
    public final static String CHANGE_EMAIL_ERROR="message.change.email.error";

    /** The Constant CHANGE_LOGIN_ERROR. */
    public final static String CHANGE_LOGIN_ERROR="message.change.login.error";

    /** The Constant CHANGE_PASS_EQUAL_ERROR. */
    public final static String CHANGE_PASS_EQUAL_ERROR="message.change.pass.equal";

    /** The Constant CHANGE_PASS_EQUAL_NEW_ERROR. */
    public final static String CHANGE_PASS_EQUAL_NEW_ERROR="message.change.pass.equal.new";

    /** The Constant CHANGE_PASS_NEW_ERROR. */
    public final static String CHANGE_PASS_NEW_ERROR="message.change.pass.new";

    /** The Constant CHANGE_SUCCESS. */
    public final static String CHANGE_SUCCESS="message.change.success";

    /** The Constant CHANGE_MATCH_TOURNAMENT_ERROR. */
    public final static String CHANGE_MATCH_TOURNAMENT_ERROR="message.change.track.name.error";

    /** The Constant DELETE_MATCH_SUCCESS. */
    public final static String DELETE_MATCH_SUCCESS="message.delete.match.success";


    /** The Constant LOGIN_ERROR. */
    public final static String LOGIN_ERROR="message.login.error";

    /** The Constant NOT_UNIQUE_EMAIL. */
    public final static String NOT_UNIQUE_EMAIL="message.signup.unique.email";

    /** The Constant NOT_UNIQUE_LOGIN. */
    public final static String NOT_UNIQUE_LOGIN="message.signup.unique.login";


    /** The Constant BET_ERROR. */
    public final static String ADD_BET_ERROR="message.add.bet.error";

    /** The Constant BET_SUCCESS. */
    public final static String ADD_BET_SUCCESS="message.add.bet.success";

    /** The Constant SIGNUP_ERROR. */
    public final static String SIGNUP_ERROR="message.signup.error";



    /** The resource bundle. */
    private  ResourceBundle resourceBundle ;

    /**
     * Instantiates a new message manager.
     */
    public MessageManager() {
        resourceBundle=ResourceBundleType.RU_RU.getResourceBundle();
    }

    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    /**
     * Sets the current locale.
     *
     * @param language the new current locale
     */
    public void setCurrentLocale(String language) {
        if("en_US".equals(language)){
            resourceBundle = ResourceBundleType.EN_US.getResourceBundle();
        }else {
            resourceBundle = ResourceBundleType.RU_RU.getResourceBundle();
        }
    }
}

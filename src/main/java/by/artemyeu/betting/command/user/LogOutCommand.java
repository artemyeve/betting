package by.artemyeu.betting.command.user;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */
public class LogOutCommand extends AbstractCommand {

    /** The Constant IS_LOGIN. */
    private static final String IS_LOGIN="is_login";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        sessionRequestContent.setSessionAttribute(IS_LOGIN, null);
        sessionRequestContent.setSessionAttribute(USER_ATTRIBUTE, null);
        return ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
    }
}

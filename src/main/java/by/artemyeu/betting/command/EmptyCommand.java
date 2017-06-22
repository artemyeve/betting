package by.artemyeu.betting.command;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 16.05.2017.
 */

public class EmptyCommand extends AbstractCommand {

    /** The Constant DEFAULT_LOCALE. */
    private static final String DEFAULT_LOCALE = "ru_RU";

    /* (non-Javadoc)
     * @see src.main.java.AbstractCommand#execute(SessionRequestContent)
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        sessionRequestContent.setSessionAttribute(CUR_PAGE_ATTR,ConfigurationManager.HOME_PATH);
        sessionRequestContent.setSessionAttribute(LOCALE_ATTRIBUTE, DEFAULT_LOCALE );
        return page;
    }
}

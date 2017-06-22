package by.artemyeu.betting.command;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;


/**
 * Created by Acer on 16.05.2017.
 */
public class IndexCommand extends AbstractCommand {

    /** The Constant DEFAULT_LOCALE. */
    private final String DEFAULT_LOCALE = "ru_RU";


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {

        sessionRequestContent.setSessionAttribute(LOCALE_ATTRIBUTE, DEFAULT_LOCALE);
        return ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
    }
}

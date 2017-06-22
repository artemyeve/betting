package by.artemyeu.betting.command.visitor;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 06.06.2017.
 */
public class ChangeLanguageCommand extends AbstractCommand {

    /** The Constant LANGUAGE_ATTRIBUTE. */
    private static final String LANGUAGE_ATTRIBUTE = "lang";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        Object str = sessionRequestContent.getRequestParameter(LANGUAGE_ATTRIBUTE);
        if (str != null) {
            messageManager.setCurrentLocale(str.toString());
            sessionRequestContent.setSessionAttribute(LOCALE_ATTRIBUTE, str.toString());
        }
        Object property = sessionRequestContent.getSessionAttribute(CUR_PAGE_ATTR);
        if (property == null) {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        } else {
            page = ConfigurationManager.getProperty(property.toString());
        }
        return page;
    }
}

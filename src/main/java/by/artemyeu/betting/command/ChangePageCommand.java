package by.artemyeu.betting.command;

import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * The Class ChangePageCommand.
 */
public class ChangePageCommand extends AbstractCommand {

    /** The page param. */
    private final String PAGE_PARAM = "page";

    /** The num of pages. */
    private final String NUM_OF_PAGES = "number_of_pages";


    /* (non-Javadoc)
     * @see src.main.java.AbstractCommand#execute(SessionRequestContent)
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        Object pageObj = sessionRequestContent.getRequestParameter(PAGE_PARAM);
        Object allPagesObj = sessionRequestContent.getRequestParameter(NUM_OF_PAGES);
        int newPageNum;
        if (pageObj != null) {
            newPageNum = Integer.valueOf(pageObj.toString());
            if (allPagesObj == null || newPageNum > Integer.valueOf(allPagesObj.toString())){
                newPageNum = 0;
            }
        } else {
            newPageNum = 0;
        }
        sessionRequestContent.setSessionAttribute(NUM_PAGE, newPageNum);
        return ConfigurationManager.getProperty(sessionRequestContent.getSessionAttribute(CUR_PAGE_ATTR).toString());
    }
}


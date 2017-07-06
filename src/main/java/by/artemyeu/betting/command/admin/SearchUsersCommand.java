package by.artemyeu.betting.command.admin;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.UserLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
public class SearchUsersCommand extends AbstractCommand {
    private final String FIND_PARAMETER = "find";
    private final String USER_LIST_ATTR = "users";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
        if (user != null && user.getRole() == UserRole.ADMIN) {
            UserLogic userLogic = new UserLogic();
            try {
                String str = sessionRequestContent.getRequestParameter(FIND_PARAMETER);
                List<User> userList = userLogic.findSuitableUsers(str);
                sessionRequestContent.setSessionAttribute(USER_LIST_ATTR, userList);
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during users search", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

package by.artemyeu.betting.command.user;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.BetLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 20.06.2017.
 */
public class AddBetCommand extends AbstractCommand {
    /** The Constant RESULT_ATTR. */
    private static final String RESULT_ATTR = "result";

    /** The bet amount param. */
    private final String BET_AMOUNT_PARAM = "bet_amount";
    /** The bet date param. */
    private final String BET_DATE_PARAM = "bet_date";
    /** The active param. */
    private final String ACTIVE_PARAM = "active";
    /** The user id param. */
    private final String USER_ID_PARAM = "user_id";

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
        if (user != null && user.getRole() == UserRole.BETTOR) {
            boolean result = Boolean.valueOf(sessionRequestContent.getRequestAttribute(RESULT_ATTR).toString());
            if (result) {
                BetLogic betLogic = new BetLogic();
                String betAmount = sessionRequestContent.getRequestParameter(BET_AMOUNT_PARAM);
                String betDate = sessionRequestContent.getRequestParameter(BET_DATE_PARAM);
                String active = sessionRequestContent.getRequestParameter(ACTIVE_PARAM);
                String userId = sessionRequestContent.getRequestParameter(USER_ID_PARAM);

                try {

                    String res = betLogic.addBet(betAmount,betDate,active,userId);
                    if (SUCCESS.equals(res)) {

                        sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.ADD_BET_SUCCESS));
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    }
                } catch (LogicException e) {
                    LOG.error("Exception during bet addition command", e);
                    sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_BET_ERROR));
                    page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                }
            } else {
                sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_BET_ERROR));
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

package by.artemyeu.betting.command.bookmaker;

import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.entity.UserRole;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.logic.BetLineLogic;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.manager.MessageManager;
import by.artemyeu.betting.servlet.SessionRequestContent;

/**
 * Created by Acer on 26.06.2017.
 */
public class AddOutcomeCommand extends AbstractCommand {

    /** The Constant RESULT_ATTR. */
    private static final String RESULT_ATTR = "result";
    /** The match id param. */
    private final String MATCH_ID_PARAMETER = "odd";
    /** The outcome param. */
    private final String OUTCOME_PARAMETER = "outcome";
    /** The odd param. */
    private final String ODD_PARAMETER = "odd";


    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
        if (user != null && user.getRole() == UserRole.BOOKMAKER) {
            boolean result = Boolean.valueOf(sessionRequestContent.getRequestAttribute(RESULT_ATTR).toString());
            if (result) {
                BetLineLogic betLineLogic = new BetLineLogic();
                String matchId = sessionRequestContent.getRequestParameter(MATCH_ID_PARAMETER);
                String outcome = sessionRequestContent.getRequestParameter(OUTCOME_PARAMETER);
                String odd = sessionRequestContent.getRequestParameter(ODD_PARAMETER);

                try {
                    String res = betLineLogic.addOutcome(matchId,outcome,odd);
                    if (SUCCESS.equals(res)) {

                        sessionRequestContent.setRequestAttribute(SUCCESS, messageManager.getProperty(MessageManager.ADD_OUTCOME_SUCCESS));
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    } else {
                        sessionRequestContent.setRequestAttribute(ERROR, res);
                        page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                    }
                } catch (LogicException e) {
                    LOG.error("Exception during outcome addition command", e);
                    sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_OUTCOME_ERROR));
                    page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
                }
            } else {
                sessionRequestContent.setRequestAttribute(ERROR, messageManager.getProperty(MessageManager.ADD_OUTCOME_ERROR));
                page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

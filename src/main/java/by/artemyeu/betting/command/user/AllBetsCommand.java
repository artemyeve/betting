package by.artemyeu.betting.command.user;

import by.artemyeu.betting.entity.Bet;
import by.artemyeu.betting.exception.LogicException;
import by.artemyeu.betting.manager.ConfigurationManager;
import by.artemyeu.betting.command.AbstractCommand;
import by.artemyeu.betting.entity.User;
import by.artemyeu.betting.logic.BetLogic;
import by.artemyeu.betting.servlet.SessionRequestContent;

import java.util.List;

/**
 * Created by Acer on 16.05.2017.
 */
public class AllBetsCommand extends AbstractCommand {
    private static final String MATCH_LIST_ATTR = "match_list";
    private static final String IS_MY_BETS = "is_my_bets";

    /**
     * Execute.
     *
     * @param sessionRequestContent the session request content
     * @return the string
     */
    @Override
    public String execute(SessionRequestContent sessionRequestContent) {
        String page;
        String logined = (String) sessionRequestContent.getSessionAttribute(IS_LOGIN);
        if (logined != null && Boolean.valueOf(logined)) {
            List<Bet> bets;
            User user = (User) sessionRequestContent.getSessionAttribute(USER_ATTRIBUTE);
            BetLogic betLogic = new BetLogic();
            try {
                bets = betLogic.findMyBets(user.getId());
                sessionRequestContent.setSessionAttribute(MATCH_LIST_ATTR, bets);
                sessionRequestContent.setRequestAttribute(IS_MY_BETS, true);
                page = ConfigurationManager.getProperty(ConfigurationManager.MY_BETS_PATH);
            } catch (LogicException e) {
                LOG.error("Exception during my bets search", e);
                page = redirectToErrorPage(sessionRequestContent, e);
            }
        } else {
            page = ConfigurationManager.getProperty(ConfigurationManager.HOME_PATH);
        }
        return page;
    }
}

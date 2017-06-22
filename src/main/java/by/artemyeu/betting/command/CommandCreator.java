package by.artemyeu.betting.command;

import by.artemyeu.betting.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Acer on 06.06.2017.
 */
public class CommandCreator {
    private static final Logger LOG = LogManager.getLogger();
    private static final String COMMAND = "command";

    /**
     * Define command.
     *
     * @param sessionRequestContent the session request content
     * @return the abstract command
     */
    public AbstractCommand defineCommand(SessionRequestContent sessionRequestContent) {
        AbstractCommand current = new EmptyCommand();

        String command = sessionRequestContent.getRequestParameter(COMMAND);
        if (command != null && !command.isEmpty()) {
            try {
                CommandType currentCommand = CommandType.valueOf(command.toUpperCase());
                current = currentCommand.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                LOG.error("Exception during command creator",e);
            }
        }
        return current;
    }
}

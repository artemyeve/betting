package by.artemyeu.betting.command;

import by.artemyeu.betting.command.admin.*;
import by.artemyeu.betting.command.admin.AllMatchesCommand;
import by.artemyeu.betting.command.user.*;
import by.artemyeu.betting.command.visitor.*;

/**
 * Created by Acer on 16.05.2017.
 */
public enum CommandType {
    ADD_FUNDS{
        {
            this.command = new AddFundsCommand();
        }
    },
    ADD_MATCH{
        {
            this.command = new AddMatchCommand();
        }
    },
    ALL{
        {
            this.command = new AllMatchesCommand();
        }
    },
    CHANGE{
        {
            this.command = new ChangePageCommand();
        }
    },
    CHANGE_PAGE{
        {
            this.command = new ChangePageCommand();
        }
    },
    CHANGE_PASS{
        {
            this.command = new ChangePasswordCommand();
        }
    },
    DELETE{
        {
            this.command = new DeleteMatchCommand();
        }
    },

    EDIT{
        {
          this.command = new EditMatchCommand();
        }
    },
    INDEX{
        {
            this.command = new IndexCommand();
        }
    },
    LANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    MAIN{
        {
            this.command = new MainCommand();
        }
    },
    MY_BETS{
        {
            this.command= new AllBetsCommand();
        }
    },

    SEARCH{
        {
            this.command = new ShowActiveMatchCommand();
        }
    },
    SEARCH_USERS{
        {
            this.command = new SearchUsersCommand();
        }
    },
    SHOW_USERS{
        {
            this.command = new ShowUsersCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SingUpCommand();
        }
    };

    AbstractCommand command;
    public AbstractCommand getCurrentCommand() {
        return command;
    }
}
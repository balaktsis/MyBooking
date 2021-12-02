package Users.Actions;

import Users.User;

public class CustomerCommandLineManager extends CommandLineManager{


    public CustomerCommandLineManager(User parentUser) {
        super(parentUser);
        this.commandsList = new Command[]{

        };
    }

}

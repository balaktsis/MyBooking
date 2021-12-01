package Users.Actions;
import Users.Actions.Admin.*;
import Users.User;


public class AdminCommandLineManager extends CommandLineManager {

    Command[] commandsList = {
            new ApproveUser(),
            new ShowBookings(),
            new ShowUsers(),
            new UserLookup(),

    };


    public AdminCommandLineManager(User parentUser) {
        super(parentUser);
    }
}



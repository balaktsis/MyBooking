package Users.Actions;
import Users.Actions.Admin.*;
import Users.User;


public class AdminCommandLineManager extends CommandLineManager {


    public AdminCommandLineManager(User parentUser) {
        super(parentUser);
        this.commandsList = new Command[]{
                new ApproveUser(),
                new ShowBookings(),
                new ShowUsers(),
                new UserLookup(),
        };
    }
}



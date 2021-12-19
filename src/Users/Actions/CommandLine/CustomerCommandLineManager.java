package Users.Actions.CommandLine;

import Users.Actions.CommandLine.Customer.*;
import Users.User;

public class CustomerCommandLineManager extends CommandLineManager{


    public CustomerCommandLineManager(User parentUser) {
        super(parentUser);
        this.commandsList = new Command[]{
                new BookLodge(),
                new CancelBooking(),
                new LodgesLookup(),
                new ShowBookings(),
                new ShowDetails(),
        };
    }

}

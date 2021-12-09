package Users.Actions;

import Users.Actions.Landlord.*;
import Users.User;


public class LandlordCommandLineManager extends CommandLineManager{

    public LandlordCommandLineManager(User parentUser) {
        super(parentUser);
        this.commandsList = new Command[]{
                new AddLodge(),
                new BookingLookup(),
                new CancelBooking(),
                new DeleteLodge(),
                new EditLodge(),
                new ShowBookings(),
                new ShowDetails(),
                new ShowLodgeBookings(),
                new ShowLodges()
        };
    }
}

package Users.Actions;

import Users.Actions.Landlord.*;
import Users.User;

public class LandlordCommandLineManager extends CommandLineManager{

    Command[] commandsList = {
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

    public LandlordCommandLineManager(User parentUser) {
        super(parentUser);
    }
}

package Users.Actions.Graphical;

import Users.Actions.Graphical.Landlord.*;
import Users.User;


public class LandlordGUIManager extends GUIManager{

    public LandlordGUIManager(User parentUser) {
        super(parentUser);
        this.guiActions = new GUIAction[]{
                new AddLodge(),
                new ShowLodges(),
                new ShowBookings(),
                new BookingLookup(),
                new ShowDetailsPanel()
        };
    }

}

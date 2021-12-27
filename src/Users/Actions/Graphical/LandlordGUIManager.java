package Users.Actions.Graphical;

import Users.Actions.Graphical.Landlord.AddLodge;
import Users.Actions.Graphical.Landlord.ShowLodges;
import Users.User;


public class LandlordGUIManager extends GUIManager{

    public LandlordGUIManager(User parentUser) {
        super(parentUser);
        this.guiActions = new GUIAction[]{
                new AddLodge(),
                new ShowLodges()
        };
    }

}

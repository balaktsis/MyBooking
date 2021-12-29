package Users.Actions.Graphical;

import Users.Actions.Graphical.Customer.LookupLodges;
import Users.User;

public class CustomerGUIManager extends GUIManager{
    public CustomerGUIManager(User parentUser) {
        super(parentUser);
        this.guiActions = new GUIAction[]{
            new LookupLodges(),

        };
    }
}

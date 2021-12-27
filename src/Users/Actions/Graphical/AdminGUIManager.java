package Users.Actions.Graphical;

import Users.Actions.Graphical.Admin.*;
import Users.User;


public class AdminGUIManager extends GUIManager{

    public AdminGUIManager(User parentUser) {
        super(parentUser);
        this.guiActions = new GUIAction[]{
                new ApproveUser(),
                new BookingsLookup(),

        };
    }

}

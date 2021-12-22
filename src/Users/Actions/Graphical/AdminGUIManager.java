package Users.Actions.Graphical;

import Users.Actions.Graphical.Admin.ApproveUser;
import Users.Actions.Graphical.Admin.testAction;
import Users.Actions.Graphical.Admin.testAction2;
import Users.Actions.Graphical.Admin.testAction3;
import Users.User;


public class AdminGUIManager extends GUIManager{

    public AdminGUIManager(User parentUser) {
        super(parentUser);
        this.guiActions = new GUIAction[]{
                new ApproveUser(),
                new testAction(),
                new testAction2(),
                new testAction3(),
        };
    }

}

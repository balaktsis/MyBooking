package Users.Actions.Graphical.Admin;

import Users.Actions.Graphical.GUIAction;
import Users.User;

import javax.swing.*;


public class ApproveUser extends GUIAction {
    @Override
    protected String getName() {
        return "Approve User";
    }

    @Override
    protected void invoke() {

        actionArea.add(new JLabel("This is the approve user action area!"));
        for (User user : User.getUsersWithApproval(false)){

            actionArea.add(user.toJPanel());
        }

    }
}

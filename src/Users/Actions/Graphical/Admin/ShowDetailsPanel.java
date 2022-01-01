package Users.Actions.Graphical.Admin;

import Users.Actions.Graphical.AdjustSize;
import Users.Actions.Graphical.UserDetailsPanel;

/**
 * This is a child of class UserDetails that applies to Administrators.
 * @author Christos Balaktsis
 */

public class ShowDetailsPanel extends UserDetailsPanel {

    @Override
    protected void invoke() {
        super.invoke();
        AdjustSize.AdjustPanelSize(mainPanel);
    }

    @Override
    protected void changeOthers() {}
}

package Users.Actions.Graphical.Admin;

import Users.Actions.Graphical.UserDetails;

/**
 * This is a child of class UserDetails that applies to Administrators.
 * @author Christos Balaktsis
 */

public class ShowDetails extends UserDetails {

    @Override
    protected void invoke() {
        super.invoke();
        setPanelSize();
    }

    @Override
    protected void changeOthers() {}
}

package Users.Actions.Graphical.Admin;

import Users.Actions.Graphical.UserDetails;

public class ShowDetails extends UserDetails {

    @Override
    protected void invoke() {
        super.invoke();
        setPanelSize();
    }

    @Override
    protected void changeOthers() {}
}

package Users;
import Users.Actions.CommandLine.AdminCommandLineManager;
import Users.Actions.CommandLine.CommandLineManager;
import Users.Actions.Graphical.AdminGUIManager;
import Users.Actions.Graphical.GUIManager;

import javax.swing.*;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Administrator User Type
 * @author Neron Panagiotopoulos
 */
public class Administrator extends User{

    /**
     * Primary constructor of an Administrator User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Administrator(String username, String password) {
        super(username, password);
        this.typeName = "Administrator";
    }

    @Override
    protected GUIManager getGUIManager() {
        return new AdminGUIManager(this);
    }

    @Override
    protected CommandLineManager getCMDManager() {
        return new AdminCommandLineManager(this);
    }


    @Override
    public String toString(){
        return "Administrator\n" +
                super.toString();
    }


    @Override
    protected void addFields(JPanel mainPanel) {
    }

}

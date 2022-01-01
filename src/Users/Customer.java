package Users;

import Users.Actions.CommandLine.CommandLineManager;
import Users.Actions.CommandLine.CustomerCommandLineManager;
import Users.Actions.Graphical.CustomerGUIManager;
import Users.Actions.Graphical.GUIManager;

import javax.swing.*;
import java.awt.*;


/**
 * This is a child of the User class, which contains functionality unique
 * to the Customer User Type
 * @author Neron Panagiotopoulos
 */
public class Customer extends User{

    private String address;

    /**
     * Primary constructor of a Customer User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Customer(String username, String password) {
        super(username, password);
        this.typeName = "Customer";
    }

    @Override
    protected GUIManager getGUIManager() {
        return new CustomerGUIManager(this);
    }

    @Override
    protected CommandLineManager getCMDManager() {
        return new CustomerCommandLineManager(this);
    }

    /**
     * Set the user's address
     * @param address String containing a user's address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * @return String containing a user's address
     */
    public String getAddress(){
        return this.address;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Customer\n");
        str.append(super.toString());
        str.append("\nAddress: ");
        str.append(this.address);
        return str.toString();
    }

    @Override
    protected void addFields(JPanel mainPanel) {
        JLabel addressLabel = new JLabel();
        JLabel addressField = new JLabel();

        //---- addressField ----
        addressLabel.setText("Address");
        mainPanel.add(addressLabel);
        addressLabel.setBounds(20, 225, 100, 20);

        //---- addressField ----
        addressField.setName("Address");
        addressField.setText(this.getAddress());
        mainPanel.add(addressField);
        addressField.setBounds(140, 225, 200, 20);
    }
}

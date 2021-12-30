package Users.Actions.Graphical.Customer;

import Users.Actions.Graphical.UserDetails;
import Users.Customer;

import javax.swing.*;

public class ShowDetails extends UserDetails {

    private final JTextField addressField = new JTextField();
    private Customer customer;

    @Override
    protected void invoke() {
        super.invoke();
        customer = (Customer) parentUser;
        JLabel addressLabel = new JLabel();

        //---- addressField ----
        addressLabel.setText("Address");
        mainPanel.add(addressLabel);
        addressLabel.setBounds(20, 225, 100, 20);

        //---- baseField ----
        addressField.setText(((Customer) parentUser).getAddress());
        mainPanel.add(addressField);
        addressField.setBounds(140, 225, 200, 20);

        setPanelSize();
    }

    @Override
    protected void changeOthers() {
        customer = (Customer) parentUser;
        customer.setAddress(addressField.getText().length()!=0 ? addressField.getText() : customer.getAddress());
    }
}

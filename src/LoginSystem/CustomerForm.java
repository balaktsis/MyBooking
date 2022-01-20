package LoginSystem;

import Users.Customer;

import java.awt.*;
import javax.swing.*;

/**
 * This a new Customer-type user registration form, extending on UserForm abstract class.
 *
 * @author Christos Balaktsis
 */
public class CustomerForm extends UserForm {
    protected final JLabel addressLabel;
    protected final JTextField addressField;

    public CustomerForm() {
        super();
        addressField = new JTextField();
        addressLabel = new JLabel();
        this.title.setText("New Customer User");
        this.title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
        this.title.setBounds(new Rectangle(new Point(15, 25), title.getPreferredSize()));
        this.addressLabel.setText("Address");
        this.frame2ContentPane.add(addressLabel);
        this.addressLabel.setBounds(50, 290, 100, 30);
        this.frame2ContentPane.add(addressField);
        this.addressField.setBounds(150, 290, 175, 30);
        this.addressField.addActionListener(this);
        this.cancelButton.setBounds(50, 335, 95, 35);
        this.createButton.setBounds(210, 335, 115, 35);
    }

    protected void createButtonMouseClicked() {
        if(areValidFields() && addressField.getText().length()>0) {
            Customer user = new Customer(usernameField.getText(), new String(passwordField.getPassword()));
            user.setFullName(fullnameField.getText());
            user.setAddress(addressField.getText());
            completeSignUp(user);
        }
        else JOptionPane.showMessageDialog(this.formFrame,"Please check again your account details!", "Invalid details", JOptionPane.ERROR_MESSAGE);
    }
}

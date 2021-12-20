package LoginSystem;

import Misc.Storage;
import Users.Customer;
import Users.Landlord;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This a new Landlord-type user registration form, extending on UserForm abstract class.
 *
 * @author Christos Balaktsis
 */
public class LandlordForm extends UserForm {
    protected JLabel baseLabel;
    protected JTextField baseField;

    public LandlordForm() {
        super();
        baseLabel = new JLabel();
        baseField = new JTextField();
        this.title.setText("New Landlord User");
        this.title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
        this.title.setBounds(new Rectangle(new Point(15, 25), title.getPreferredSize()));
        this.baseLabel.setText("Base");
        this.frame2ContentPane.add(baseLabel);
        this.baseLabel.setBounds(50, 290, 100, 30);
        this.frame2ContentPane.add(baseField);
        this.baseField.setBounds(150, 290, 175, 30);
        this.baseField.addActionListener(this);
        this.cancelButton.setBounds(50, 335, 95, 35);
        this.createButton.setBounds(210, 335, 115, 35);
    }

    protected void createButtonMouseClicked(MouseEvent e) {
        if(areValidFields() && baseField.getText().length()>0) {
            Landlord user = new Landlord(usernameField.getText(), new String(passwordField.getPassword()));
            user.setFullName(fullnameField.getText());
            user.setBase(baseField.getText());
            completeSignUp(user);
        }
        else JOptionPane.showMessageDialog(this.formFrame,"Please check again your account details!", "Invalid details", JOptionPane.ERROR_MESSAGE);
    }
}

package LoginSystem;

import Users.Administrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This a new Administrator-type user registration form, extending on UserForm abstract class.
 *
 * @author Christos Balaktsis
 */
public class AdminForm extends UserForm {

    public AdminForm() {
        super();
        this.title.setText("New Administrator User");
        this.title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
        this.title.setBounds(new Rectangle(new Point(15, 25), title.getPreferredSize()));
    }

    protected void createButtonMouseClicked(MouseEvent e) {
        if(areValidFields()) {
            Administrator user = new Administrator(usernameField.getText(), new String(passwordField.getPassword()));
            user.setFullName(fullnameField.getText());
            completeSignUp(user);
        }
        else JOptionPane.showMessageDialog(this.formFrame,"Please check again your account details!", "Invalid details", JOptionPane.ERROR_MESSAGE);
    }


}

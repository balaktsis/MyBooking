/*
 * Created by JFormDesigner on Sun Dec 19 23:44:48 EET 2021
 */

package LoginSystem;

import Misc.Storage;
import Users.Customer;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Christos Balaktsis
 */
public class CustomerForm extends LoginSystem {
    public CustomerForm() {
        super(true);
        initComponents();
    }

    private void activateWrongPasswordLabel() {
        wrongPasswordLabel.setVisible(!Arrays.equals(repPasswordField.getPassword(), passwordField.getPassword()));
    }

    private void createButtonMouseClicked(MouseEvent e) {
        if(!invalidUsernameLabel.isVisible() && !invalidPasswordLabel.isVisible() && !wrongPasswordLabel.isVisible() && usernameField.getText().length()*passwordField.getPassword().length*fullnameField.getText().length()*addressField.getText().length()>0) {
            Customer user = new Customer(usernameField.getText(), new String(passwordField.getPassword()));
            user.setAddress(addressField.getText());
            user.setFullName(fullnameField.getText());
            Storage.getUsers().add(user);
            JOptionPane.showMessageDialog(frame2,"Thank you for registering in MyBooking!" +
                    "\nYour account will be activated by our administration team as soon as possible." +
                    "\nPlease come back later!", "Account created - Pending approval", JOptionPane.INFORMATION_MESSAGE);
            frame2.dispose();
            new LoginScreen();
        }
        else
            JOptionPane.showMessageDialog(frame2,"Please check again your account details!", "Invalid details", JOptionPane.ERROR_MESSAGE);
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        frame2.dispose();
        new LoginScreen();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Christos Balaktsis
        frame2 = new JFrame();
        logo = new JLabel();
        usernameField = new JTextField();
        usernameLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordLabel = new JLabel();
        repPasswordLabel = new JLabel();
        repPasswordField = new JPasswordField();
        fullnameField = new JTextField();
        fullnameLabel = new JLabel();
        noteLabel = new JLabel();
        createButton = new JButton();
        cancelButton = new JButton();
        wrongPasswordLabel = new JLabel();
        title = new JLabel();
        invalidPasswordLabel = new JLabel();
        invalidUsernameLabel = new JLabel();
        addressLabel = new JLabel();
        addressField = new JTextField();

        //======== frame2 ========
        {
            frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame2.setAlwaysOnTop(true);
            frame2.setBackground(Color.white);
            frame2.setTitle("MyBooking - Sign-Up");
            frame2.setIconImage(new ImageIcon(getClass().getResource("/Misc/images/logoIcon.png")).getImage());
            frame2.setResizable(false);
            var frame2ContentPane = frame2.getContentPane();
            frame2ContentPane.setLayout(null);

            //---- logo ----
            logo.setIcon(new ImageIcon(getClass().getResource("/Misc/images/logoIcon.png")));
            logo.setHorizontalAlignment(SwingConstants.CENTER);
            frame2ContentPane.add(logo);
            logo.setBounds(130, 365, 125, 110);
            frame2ContentPane.add(usernameField);
            usernameField.setBounds(150, 95, 175, 30);

            //---- usernameLabel ----
            usernameLabel.setText("Username");
            frame2ContentPane.add(usernameLabel);
            usernameLabel.setBounds(50, 95, 100, 30);

            //---- passwordField ----
            passwordField.setToolTipText("Password should be 6 to 20 characters, including at least 1 digit.");
            frame2ContentPane.add(passwordField);
            passwordField.setBounds(150, 145, 175, 30);

            //---- passwordLabel ----
            passwordLabel.setText("Password");
            frame2ContentPane.add(passwordLabel);
            passwordLabel.setBounds(50, 145, 100, 30);

            //---- repPasswordLabel ----
            repPasswordLabel.setText("Repeat Password");
            frame2ContentPane.add(repPasswordLabel);
            repPasswordLabel.setBounds(50, 195, 100, 30);
            frame2ContentPane.add(repPasswordField);
            repPasswordField.setBounds(150, 195, 175, 30);
            frame2ContentPane.add(fullnameField);
            fullnameField.setBounds(150, 245, 175, 30);

            //---- fullnameLabel ----
            fullnameLabel.setText("Full Name");
            frame2ContentPane.add(fullnameLabel);
            fullnameLabel.setBounds(50, 245, 100, 30);

            //---- noteLabel ----
            noteLabel.setText("Please, fill in the following information and your account will be activated soon!");
            noteLabel.setFont(noteLabel.getFont().deriveFont(noteLabel.getFont().getSize() - 2f));
            frame2ContentPane.add(noteLabel);
            noteLabel.setBounds(15, 55, 360, 20);

            //---- createButton ----
            createButton.setText("Create account");
            createButton.setForeground(new Color(0, 204, 51));
            createButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    createButtonMouseClicked(e);
                }
            });
            frame2ContentPane.add(createButton);
            createButton.setBounds(210, 335, 115, 35);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.setForeground(new Color(204, 0, 51));
            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cancelButtonMouseClicked(e);
                }
            });
            frame2ContentPane.add(cancelButton);
            cancelButton.setBounds(50, 335, 95, 35);

            //---- wrongPasswordLabel ----
            wrongPasswordLabel.setText("Passwords are not matching.");
            wrongPasswordLabel.setFont(wrongPasswordLabel.getFont().deriveFont(wrongPasswordLabel.getFont().getSize() - 1f));
            wrongPasswordLabel.setForeground(Color.red);
            frame2ContentPane.add(wrongPasswordLabel);
            wrongPasswordLabel.setBounds(155, 225, 180, wrongPasswordLabel.getPreferredSize().height);

            //---- title ----
            title.setText("New Customer User");
            title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
            frame2ContentPane.add(title);
            title.setBounds(new Rectangle(new Point(15, 25), title.getPreferredSize()));

            //---- invalidPasswordLabel ----
            invalidPasswordLabel.setText("Invalid format for password.");
            invalidPasswordLabel.setFont(invalidPasswordLabel.getFont().deriveFont(invalidPasswordLabel.getFont().getSize() - 1f));
            invalidPasswordLabel.setForeground(Color.red);
            frame2ContentPane.add(invalidPasswordLabel);
            invalidPasswordLabel.setBounds(155, 175, 180, 15);

            //---- invalidUsernameLabel ----
            invalidUsernameLabel.setText("Username is already in use.");
            invalidUsernameLabel.setFont(invalidUsernameLabel.getFont().deriveFont(invalidUsernameLabel.getFont().getSize() - 1f));
            invalidUsernameLabel.setForeground(Color.red);
            frame2ContentPane.add(invalidUsernameLabel);
            invalidUsernameLabel.setBounds(155, 125, 180, 15);

            //---- addressLabel ----
            addressLabel.setText("Address");
            frame2ContentPane.add(addressLabel);
            addressLabel.setBounds(50, 290, 100, 30);
            frame2ContentPane.add(addressField);
            addressField.setBounds(150, 290, 175, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame2ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frame2ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame2ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame2ContentPane.setMinimumSize(preferredSize);
                frame2ContentPane.setPreferredSize(preferredSize);
            }
            frame2.setSize(380, 515);
            frame2.setLocationRelativeTo(frame2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        frame2.setVisible(true);
        wrongPasswordLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        repPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                activateWrongPasswordLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                activateWrongPasswordLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                activateWrongPasswordLabel();
            }
        });
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                invalidPasswordLabel.setVisible(LoginSystem.checkPassword(new String(passwordField.getPassword())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                invalidPasswordLabel.setVisible(LoginSystem.checkPassword(new String(passwordField.getPassword())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                invalidPasswordLabel.setVisible(LoginSystem.checkPassword(new String(passwordField.getPassword())));
            }
        });
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                invalidUsernameLabel.setVisible(LoginSystem.checkUsername(usernameField.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                invalidUsernameLabel.setVisible(LoginSystem.checkUsername(usernameField.getText()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                invalidUsernameLabel.setVisible(LoginSystem.checkUsername(usernameField.getText()));
            }
        });
        usernameField.addActionListener(e -> createButtonMouseClicked(null));
        passwordField.addActionListener(e -> createButtonMouseClicked(null));
        repPasswordField.addActionListener(e -> createButtonMouseClicked(null));
        fullnameField.addActionListener(e -> createButtonMouseClicked(null));
        addressField.addActionListener(e -> createButtonMouseClicked(null));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Christos Balaktsis
    private JFrame frame2;
    private JLabel logo;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel repPasswordLabel;
    private JPasswordField repPasswordField;
    private JTextField fullnameField;
    private JLabel fullnameLabel;
    private JLabel noteLabel;
    private JButton createButton;
    private JButton cancelButton;
    private JLabel wrongPasswordLabel;
    private JLabel title;
    private JLabel invalidPasswordLabel;
    private JLabel invalidUsernameLabel;
    private JLabel addressLabel;
    private JTextField addressField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

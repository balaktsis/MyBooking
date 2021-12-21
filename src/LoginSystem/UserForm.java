package LoginSystem;

import Misc.Storage;
import Users.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;


/**
 * This is a user sign-up abstract class that represents a new account creation form. A new account,
 * after being created, has to get marked as approved by an administrator, otherwise log-in is prohibited.
 * @author Christos Balaktsis
 */
public abstract class UserForm implements MouseListener, ActionListener {
    protected JFrame formFrame;
    protected JLabel logo;
    protected JTextField usernameField;
    protected JLabel usernameLabel;
    protected JPasswordField passwordField;
    protected JLabel passwordLabel;
    protected JLabel repPasswordLabel;
    protected JPasswordField repPasswordField;
    protected JTextField fullnameField;
    protected JLabel fullnameLabel;
    protected JLabel noteLabel;
    protected JButton createButton;
    protected JButton cancelButton;
    protected JLabel wrongPasswordLabel;
    protected JLabel title;
    protected JLabel invalidPasswordLabel;
    protected JLabel invalidUsernameLabel;
    protected Container frame2ContentPane;

    public UserForm() {
        formFrame = new JFrame();
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
        frame2ContentPane = formFrame.getContentPane();
        frame2ContentPane.setLayout(null);

        //======== formFrame ========
        formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formFrame.setBackground(Color.white);
        formFrame.setTitle("MyBooking - Sign-Up");
        formFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
        formFrame.setResizable(false);

        //---- logo ----
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        frame2ContentPane.add(logo);
        logo.setBounds(130, 345, 125, 110);
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
        createButton.addMouseListener(this);
        frame2ContentPane.add(createButton);
        createButton.setBounds(210, 300, 115, 35);

        //---- cancelButton ----
        cancelButton.setText("Cancel");
        cancelButton.setForeground(new Color(204, 0, 51));
        cancelButton.addMouseListener(this);
        frame2ContentPane.add(cancelButton);
        cancelButton.setBounds(50, 300, 95, 35);

        //---- wrongPasswordLabel ----
        wrongPasswordLabel.setText("Passwords are not matching.");
        wrongPasswordLabel.setFont(wrongPasswordLabel.getFont().deriveFont(wrongPasswordLabel.getFont().getSize() - 1f));
        wrongPasswordLabel.setForeground(Color.red);
        frame2ContentPane.add(wrongPasswordLabel);
        wrongPasswordLabel.setBounds(155, 225, 180, wrongPasswordLabel.getPreferredSize().height);

        //---- title ----
        title.setText("");
        frame2ContentPane.add(title);

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
        formFrame.setSize(380, 510);
        formFrame.setLocationRelativeTo(formFrame.getOwner());

        formFrame.setVisible(true);
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
        usernameField.addActionListener(this);
        passwordField.addActionListener(this);
        repPasswordField.addActionListener(this);
        fullnameField.addActionListener(this);
        cancelButton.addActionListener(e -> cancelButtonMouseClicked(null));
    }

    abstract void createButtonMouseClicked(MouseEvent e);

    private void activateWrongPasswordLabel() {
        wrongPasswordLabel.setVisible(!Arrays.equals(repPasswordField.getPassword(), passwordField.getPassword()));
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        this.formFrame.dispose();
        new LoginScreen();
    }

    protected void completeSignUp(User user) {
        Storage.getUsers().add(user);
        JOptionPane.showMessageDialog(this.formFrame,"Thank you for registering in MyBooking!" +
                "\nYour account will be activated by our administration team as soon as possible." +
                "\nPlease come back later!", "Account created - Pending approval", JOptionPane.INFORMATION_MESSAGE);
        this.formFrame.dispose();
        new LoginScreen();
    }

    protected boolean areValidFields() {
        return !invalidUsernameLabel.isVisible() && !invalidPasswordLabel.isVisible() && !wrongPasswordLabel.isVisible()
                && usernameField.getText().length()*passwordField.getPassword().length*fullnameField.getText().length()>0;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(createButton)) createButtonMouseClicked(e);
        else if(e.getSource().equals(cancelButton)) cancelButtonMouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!e.getSource().equals(cancelButton) && !e.getSource().equals(createButton)) createButtonMouseClicked(null);
    }

}

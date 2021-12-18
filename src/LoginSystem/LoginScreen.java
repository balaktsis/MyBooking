package LoginSystem;

import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Christos Balaktsis
 */
public class LoginScreen extends LoginSystem {
    public LoginScreen() {
        super(true);
        initComponents();
    }

    private void forgotPassLabelMouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(this.frame1,"Relax. Focus and try to remember it!","Forgot password",JOptionPane.INFORMATION_MESSAGE );
    }

    private void resetLoginWindow() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocusInWindow();
    }

    private void signInButtonMouseClicked(MouseEvent e) {
        User user = LoginSystem.checkUser(usernameField.getText(),String.valueOf(passwordField.getPassword()));
        if(user == null) {
            JOptionPane.showMessageDialog(this.frame1, "Username or password does not match to any" +
                    " existing account!\nPlease try again!","Wrong Credentials", JOptionPane.ERROR_MESSAGE);
            resetLoginWindow();
        }
        else {
            frame1.setVisible(false);
            user.showInterface(false);
            resetLoginWindow();
            frame1.setVisible(true);
        }
    }

    private void usernameFieldFocusGained(FocusEvent e) {
        passwordField.setText("");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        frame1 = new JFrame();
        logo = new JLabel();
        usernameField = new JTextField();
        usernameLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordLabel = new JLabel();
        forgotPassLabel = new JLabel();
        welcomeLabel = new JLabel();
        signInButton = new JButton();
        signUpButton = new JButton();

        //======== frame1 ========
        {
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame1.setAlwaysOnTop(true);
            frame1.setBackground(Color.white);
            frame1.setTitle("MyBooking");
            frame1.setIconImage(new ImageIcon(getClass().getResource("/Misc/images/logoIcon.png")).getImage());
            frame1.setResizable(false);
            var frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- logo ----
            logo.setIcon(new ImageIcon(getClass().getResource("/Misc/images/logoIcon.png")));
            logo.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(logo);
            logo.setBounds(new Rectangle(new Point(115, 30), logo.getPreferredSize()));

            //---- usernameField ----
            usernameField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    usernameFieldFocusGained(e);
                }
            });
            frame1ContentPane.add(usernameField);
            usernameField.setBounds(65, 240, 225, 35);

            //---- usernameLabel ----
            usernameLabel.setText("Username");
            usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            frame1ContentPane.add(usernameLabel);
            usernameLabel.setBounds(70, 215, 70, 25);
            frame1ContentPane.add(passwordField);
            passwordField.setBounds(65, 300, 225, 35);

            //---- passwordLabel ----
            passwordLabel.setText("Password");
            passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            frame1ContentPane.add(passwordLabel);
            passwordLabel.setBounds(70, 275, 70, 25);

            //---- forgotPassLabel ----
            forgotPassLabel.setText("Forgot Password?");
            forgotPassLabel.setFont(forgotPassLabel.getFont().deriveFont(forgotPassLabel.getFont().getSize() - 2f));
            forgotPassLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    forgotPassLabelMouseClicked(e);
                }
            });
            frame1ContentPane.add(forgotPassLabel);
            forgotPassLabel.setBounds(new Rectangle(new Point(210, 340), forgotPassLabel.getPreferredSize()));

            //---- welcomeLabel ----
            welcomeLabel.setText("Welcome to MyBooking!");
            welcomeLabel.setBackground(Color.white);
            welcomeLabel.setFont(new Font("Montserrat ExtraBold", Font.PLAIN, 16));
            welcomeLabel.setForeground(new Color(16, 143, 233));
            frame1ContentPane.add(welcomeLabel);
            welcomeLabel.setBounds(new Rectangle(new Point(65, 170), welcomeLabel.getPreferredSize()));

            //---- signInButton ----
            signInButton.setText("Sign-In");
            signInButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    signInButtonMouseClicked(e);
                }
            });
            frame1ContentPane.add(signInButton);
            signInButton.setBounds(new Rectangle(new Point(210, 370), signInButton.getPreferredSize()));

            //---- signUpButton ----
            signUpButton.setText("Sign-Up");
            frame1ContentPane.add(signUpButton);
            signUpButton.setBounds(new Rectangle(new Point(65, 370), signUpButton.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame1ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frame1ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame1ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame1ContentPane.setMinimumSize(preferredSize);
                frame1ContentPane.setPreferredSize(preferredSize);
            }
            frame1.setSize(355, 485);
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        frame1.setVisible(true);
        frame1.setTitle("MyBooking");
        usernameField.requestFocusInWindow();
        usernameField.addActionListener(e -> signInButtonMouseClicked(null));
        passwordField.addActionListener(e -> signInButtonMouseClicked(null));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame frame1;
    private JLabel logo;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel forgotPassLabel;
    private JLabel welcomeLabel;
    private JButton signInButton;
    private JButton signUpButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

package LoginSystem;

import Users.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

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

    private void signInButtonMouseClicked() {
        User user = LoginSystem.checkUser(usernameField.getText(),String.valueOf(passwordField.getPassword()));
        if(user == null) {
            JOptionPane.showMessageDialog(this.frame1, "Username or password does not match to any" +
                    " existing account!\nPlease try again!","Wrong Credentials", JOptionPane.ERROR_MESSAGE);

        }
         else {
            frame1.setVisible(false);
            user.showInterface(false);
            resetLoginWindow();
            frame1.setVisible(true);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Christos Balaktsis
        frame1 = new JFrame();
        logo = new JLabel();
        usernameField = new JTextField();
        usernameLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordLabel = new JLabel();
        signInButton = new JButton();
        signUUpButton = new JButton();
        forgotPassLabel = new JLabel();
        welcomeLabel = new JLabel();

        //======== frame1 ========
        {
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame1.setAlwaysOnTop(true);
            frame1.setBackground(Color.white);
            frame1.setTitle("MyBooking");
            try { frame1.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
            } catch (NullPointerException ignored) {}
            frame1.setResizable(false);
            var frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- logo ----
            try {
                logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))));
                logo.setHorizontalAlignment(SwingConstants.CENTER);
                frame1ContentPane.add(logo);
                logo.setBounds(new Rectangle(new Point(115, 30), logo.getPreferredSize()));
            } catch (NullPointerException e) { logo.setText("MyBooking"); }

            //---- usernameField ----
            frame1ContentPane.add(usernameField);
            usernameField.setBounds(65, 240, 225, 35);
            usernameField.addActionListener(e -> signInButtonMouseClicked());
            usernameField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    passwordField.setText("");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    passwordField.setText("");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    passwordField.setText("");
                }
            });

            //---- usernameLabel ----
            usernameLabel.setText("Username");
            usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            frame1ContentPane.add(usernameLabel);
            usernameLabel.setBounds(70, 215, 70, 25);
            frame1ContentPane.add(passwordField);
            passwordField.setBounds(65, 300, 225, 35);
            passwordField.addActionListener(e -> signInButtonMouseClicked());

            //---- passwordLabel ----
            passwordLabel.setText("Password");
            passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            frame1ContentPane.add(passwordLabel);
            passwordLabel.setBounds(70, 275, 70, 25);

            //---- signInButton ----
            signInButton.setText("Sign-in");
            signInButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    signInButtonMouseClicked();
                }
            });
            frame1ContentPane.add(signInButton);
            signInButton.setBounds(210, 370, 80, 30);

            //---- signUUpButton ----
            signUUpButton.setText("Sign-up");
            frame1ContentPane.add(signUUpButton);
            signUUpButton.setBounds(65, 370, 80, 30);

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
    }

    private JFrame frame1;
    private JLabel logo;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton signInButton;
    private JButton signUUpButton;
    private JLabel forgotPassLabel;
    private JLabel welcomeLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

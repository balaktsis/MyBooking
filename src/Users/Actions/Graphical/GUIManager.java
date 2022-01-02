package Users.Actions.Graphical;

import LoginSystem.LoginScreen;
import Misc.AppSystem;
import Misc.Storage;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Objects;


public class GUIManager implements Serializable {

    protected User parentUser;
    protected GUIAction[] guiActions;
    JFrame userGUI;

    public GUIManager(User parentUser) {
        this.parentUser = parentUser;
    }

    public void showInterface(){
        userGUI = new JFrame("My Booking - " + parentUser.getFullName());


        // On window close, run the app-system exit handler, to save unsaved data
        userGUI.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are you sure to close the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    AppSystem.exit();
                }
            }
        };
        userGUI.addWindowListener(exitListener);


        // Use logo as the app image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png")));
        userGUI.setIconImage(imageIcon.getImage());


        // Set appropriate size
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeFactor = 2;
        userGUI.setSize((int)screensize.getWidth()/sizeFactor, (int)screensize.getHeight()/sizeFactor);
        userGUI.setResizable(true);
        userGUI.setLocationRelativeTo(null);
        Dimension minSize = new Dimension((int)screensize.getWidth()/3, (int)screensize.getHeight()/3);
        userGUI.setMinimumSize(minSize);


        // Segmenting the JFrame into usable panels
        userGUI.setLayout(new BorderLayout());

        //TOP AREA: Will contain user details and a logout button
        JPanel topArea = new JPanel();
        userGUI.add(topArea, BorderLayout.BEFORE_FIRST_LINE);
        topArea.setBorder(BorderFactory.createTitledBorder(parentUser.getFullName()));

        //Button Area: Select what action should be invoked
        JPanel buttonArea = new JPanel();
        userGUI.add(buttonArea, BorderLayout.LINE_START);
        buttonArea.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.Y_AXIS));

        //Output of the actions
        JPanel actionArea = new JPanel();
        userGUI.add(actionArea, BorderLayout.CENTER);
        actionArea.setBorder(BorderFactory.createTitledBorder("Welcome"));

        //Messaging exchange panel
        JPanel messageArea = new JPanel();
        userGUI.add(messageArea, BorderLayout.LINE_END);
        messageArea.setBorder(BorderFactory.createTitledBorder("Messages"));

        //List the action buttons and initialize them with the correct params
        for (var action : this.guiActions){
            action.setParentUser(parentUser);
            buttonArea.add(action.makeButton());
            action.setActionArea(actionArea, buttonArea);
            buttonArea.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        /////////////////////////////////////////////////////////////
        topArea.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("src/Misc/images/User_icon.png");
        ImageIcon scaledInstance = new ImageIcon(image.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        JLabel userIcon = new JLabel(scaledInstance, JLabel.LEFT);
        userIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentUser.showDetailsDialog();
            }
        });
        topArea.add(userIcon, BorderLayout.WEST);

        JPanel logoutSegment = new JPanel();
        var logout = new JButton("Log out");
        logout.addActionListener(e -> {
            userGUI.dispose();
            AppSystem.getLogin();
        });
        logoutSegment.add(logout);
        topArea.add(logoutSegment, BorderLayout.EAST);

        JPanel centerSegment = new JPanel();
        centerSegment.setLayout(new GridLayout(3, 1));
        centerSegment.add(new JLabel("#" + parentUser.getUserId()));
        centerSegment.add(new JLabel("Full Name: " + parentUser.getFullName()));
        centerSegment.add(new JLabel("Username: " + parentUser.getUsername()));
        topArea.add(centerSegment, BorderLayout.CENTER);



        actionArea.add(new JLabel("Welcome " + parentUser.getFullName() + "!"));

        messageArea.add(new Button("Test0"));
        messageArea.add(new Button("Test1"));
        messageArea.add(new Button("Test2"));
        messageArea.add(new Button("Test3"));

        userGUI.setVisible(true);
    }

}

package Users;

import Misc.Message;
import Misc.Storage;
import Misc.UniqueIDGenerator;
import Users.Actions.CommandLine.CommandLineManager;
import Users.Actions.Graphical.AdjustSize;
import Users.Actions.Graphical.GUIManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a parent User class, which contains core functionality that will be
 * present in all types of users of the project
 *
 * @author Neron Panagiotopoulos
 */

abstract public class User implements Serializable {

    protected final String uid;
    protected boolean approved;
    protected String username, password;
    protected String fullName;
  //  protected List<Message> messages;
    protected String typeName;

    /**
     * Primary constructor of a User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public User(String username, String password) {
        Storage.getUsers().add(this);
        this.uid = UniqueIDGenerator.getUniqueId();
        this.username = username;
        this.password = password;
        this.approved = false;
    }

    /**
     * @return Unique ID assigned to the User object.
     */
    public String getUserId() {
        return this.uid;
    }

    /**
     * Update the username of the current user.
     *
     * @param username New username for the user
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the username string from the User object
     *
     * @return String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Update the password of the current user.
     *
     * @param password New password for the user
     */
    public boolean setPassword(String password) {
        if(password == null || !password.matches(".*[0-9]+.*") || password.length() < 6 || password.length() > 20) {
            return false;
        }
        this.password = password;
        return true;
    }

    /**
     * Get the password string from the user.
     *
     * @return String
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Set the full name of a user
     *
     * @param fullName Setting the full name of a user
     */
    public void setFullName(String fullName) {
        this.fullName = (fullName == null || fullName.isBlank()) ? null : fullName;
    }

    public String getFullName() { return this.fullName;}

    /**
     * Approve or revoke approval of a user
     *
     * @param approvalStatus Setting the approval status of a user
     */
    public void setApprovalStatus(boolean approvalStatus) {
        this.approved = approvalStatus;
    }

    /**
     * Return if the User has been approved or not
     *
     * @return Boolean corresponding to the approval status
     */
    public boolean getApprovalStatus() {
        return this.approved;
    }

    /**
     * Get a string of the type of the user
     *
     * @return String
     */
    public String getUserType(){
        return this.typeName;
    }

//    private void updateMessages() {
//        //TODO:get list of messages
//    }

//    public Message[] getMessages() {
//        updateMessages();
//        return messages;


    public boolean sendMessageTo(User recipient, String body) {
        if(Storage.getUsers().contains(recipient) && body != null && this!=recipient) {
                return Storage.getMessages().add(new Message(this, recipient, body));
        } else return false;
    }

    /**
     * Generate a GUI manager on call
     * @return GUIManager
     */
    protected abstract GUIManager getGUIManager();

    /**
     * Generate a commandline manager on call
     * @return CommandLineManager
     */
    protected abstract CommandLineManager getCMDManager();

    /**
     * Get the user interface from the perspective of a user.
     *
     * @param graphical Boolean toggle between graphical interface and command line interface.\n(CLI - False, GUI - True)
     */
    public void showInterface(boolean graphical) {
        if (graphical) {
            getGUIManager().showInterface();
        } else {
            getCMDManager().showInterface();
        }

    }


    /**
     * In order to check if a user is already registered in the system, it's necessary to override equals and hashCode
     * methods, to define when two users are "equal".
     *
     * @param user A user (parent or child) object.
     * @return Boolean value of equality check.
     */
    @Override
    public boolean equals(Object user) {

        if (user instanceof User ptr) {
            return ptr.username.equals(this.username) &&
                    ptr.password.equals(this.getPassword());
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 37 * hash + (this.uid != null ? this.uid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Username: ");
        str.append(this.username);
        str.append("\n");
        str.append("Full Name: ");
        str.append(this.fullName);
        str.append("\n");
        str.append("Account status: ");
        if (!approved) str.append("Not ");
        str.append("Approved");
        return str.toString();
    }

    /**
     * API - Get all users that match an approval status
     * @param approval_status boolean
     * @return List of users
     */
    public static List<User> getUsersWithApproval(boolean approval_status){
        List<User> users = new ArrayList<>();
        for (User user : Storage.getUsers()){
            if (user.getApprovalStatus() == approval_status){
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Get a user object from a username string
     * @param username - String
     * @return User object
     */
    public static User getUserFromUsername(String username){
        for (User user : Storage.getUsers()){
            if (user.getUsername().toLowerCase().equals(username)){
                return user;
            }
        }
        return null;
    }


    /**
     * Create a JPanel containing the details of the user, like in toString()
     * @return JPanel
     */
    public JPanel toJPanel(){
        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.white);
        userPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        userPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel imagePanel = new JPanel();
        ImageIcon image = new ImageIcon("src/Misc/images/User_icon.png");
        ImageIcon scaledInstance = new ImageIcon(image.getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING));
        imagePanel.setBackground(Color.white);
        imagePanel.add(new JLabel(scaledInstance));

        JPanel infopanel = new JPanel();
        infopanel.setLayout(new GridLayout(1, 2));

        JPanel profile = new JPanel();
        profile.setLayout(new GridLayout(2, 1));

        profile.add(new JLabel("Username: " + this.getUsername()));
        profile.add(new JLabel("Full Name: " + this.getFullName()));
        profile.setBackground(Color.white);

        JPanel details = new JPanel();
        details.setLayout(new GridLayout(2, 1));

        details.add(new JLabel("# " + this.getUserId(), SwingConstants.RIGHT));
        details.add(new JLabel(this.getUserType(), SwingConstants.RIGHT));
        details.setBackground(Color.white);

        infopanel.add(profile);
        infopanel.add(details);

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, infopanel);
        jSplitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        jSplitPane.setDividerSize(0);
        userPanel.add(jSplitPane);

        userPanel.setMaximumSize(new Dimension((int)userPanel.getPreferredSize().getWidth(), (int)userPanel.getPreferredSize().getHeight()));
//        userPanel.setMaximumSize(infopanel.getPreferredSize());

        jSplitPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetailsDialog();
            }
        });

        return userPanel;
    }


    public void showDetailsDialog(){
        JFrame dialogPanel = new JFrame();
        JPanel mainPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        JLabel noteLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        JLabel usernameField = new JLabel();
        JLabel fullnameField = new JLabel();
        JLabel fullnameLabel = new JLabel();
        JPasswordField passwordField = new JPasswordField();
        JLabel passwordLabel = new JLabel();
        JLabel roleLabel = new JLabel();
        JLabel roleField = new JLabel();
        scrollPane.add(mainPanel);
        mainPanel.setLayout(null);

        //---- noteLabel ----
        noteLabel.setText("Profile Details");
        noteLabel.setFont(noteLabel.getFont().deriveFont(noteLabel.getFont().getStyle() | Font.BOLD, noteLabel.getFont().getSize() + 5f));
        mainPanel.add(noteLabel);
        noteLabel.setBounds(20, 0, 175, 40);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        mainPanel.add(usernameLabel);
        usernameLabel.setBounds(20, 85, 100, 20);
        mainPanel.add(usernameField);
        usernameField.setText(this.getUsername());
        usernameField.setBounds(140, 85, 205, 20);

        //---- fullnameLabel ----
        fullnameLabel.setText("Full name");
        mainPanel.add(fullnameLabel);
        fullnameLabel.setBounds(20, 120, 100, 20);
        mainPanel.add(fullnameField);
        fullnameField.setText(this.getFullName());
        fullnameField.setBounds(140, 120, 100, 20);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        mainPanel.add(passwordLabel);
        passwordLabel.setBounds(20, 155, 100, 20);
        mainPanel.add(passwordField);
        passwordField.setText(this.getPassword());
        passwordField.setBounds(140, 155, 100, 20);
        passwordField.setEnabled(false);

        //---- roleLabel ----
        roleLabel.setText("Role");
        mainPanel.add(roleLabel);
        roleLabel.setBounds(20, 190, 100, 20);

        //---- roleField ----
        roleField.setText(this.getUserType());
        mainPanel.add(roleField);
        roleField.setBounds(145, 190, 100, 20);

        scrollPane.setViewportView(mainPanel);
        scrollPane.setVisible(true);

        addFields(mainPanel);

        AdjustSize.AdjustPanelSize(mainPanel);

        dialogPanel.getContentPane().add(mainPanel);
        dialogPanel.setSize(300,480);

        dialogPanel.setIconImage(new ImageIcon("src/Misc/images/tmpLodgeImage.gif").getImage());
        dialogPanel.setTitle("User Lookup");
        dialogPanel.setLocationRelativeTo(null);
        dialogPanel.setAutoRequestFocus(true);
        dialogPanel.setVisible(true);
    }

    protected abstract void addFields(JPanel mainPanel);

}

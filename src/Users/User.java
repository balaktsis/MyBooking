package Users;

import Misc.Message;
import Misc.Storage;
import Misc.UniqueIDGenerator;
import Users.Actions.CommandLineManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * This is a parent User class, which contains core functionality that will be
 * present in all types of users of the project
 *
 * @author Neron Panagiotopoulos
 */

public class User {

    protected final String uid;
    protected boolean approved;
    protected String username, password;
    protected String fullName;
    protected Message[] messages;
    protected CommandLineManager commandLineManager;


    /**
     * Primary constructor of a User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public User(String username, String password) {
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
    public void setUsername(String username) {
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
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the full name of a user
     *
     * @param fullName Setting the full name of a user
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    private void updateMessages() {
        //TODO:get list of messages
    }

    public Message[] getMessages() {
        updateMessages();
        return messages;
    }

    /**
     * Get the user interface from the perspective of a user.
     *
     * @param graphical Boolean toggle between graphical interface and command line interface.\n(CLI - False, GUI - True)
     */
    public void showInterface(boolean graphical) {
        if (graphical) {
            showGraphicalInterface();
        }
        commandLineManager.showCommandlineInterface();
    }

    protected void showGraphicalInterface() {
        //TODO 2nd part of the assignment
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
            return ptr.username.equals(this.username) && ptr.password.equals(this.password);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 37 * hash + (this.password != null ? this.password.hashCode() : 0);
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


    static public List<User> getUsersWithApproval(boolean approval_status){
        List<User> users = new ArrayList<>();
        for (User user : Storage.getUsers()){
            if (user.getApprovalStatus() == approval_status){
                users.add(user);
            }
        }
        return users;
    }

    static public User getUserFromUsername(String username){
        for (User user : Storage.getUsers()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}

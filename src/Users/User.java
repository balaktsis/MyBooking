package Users;

import Misc.Message;
import Misc.UniqueIDGenerator;

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

    protected String[] commands;


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
        showCommandlineInterface();
    }

    protected void showGraphicalInterface() {
        //TODO 2nd part of the assignment
    }


    /**
     * Begin a command-line user interface session and run the appropriate method for each command input by the user.
     */
    protected void showCommandlineInterface() {
        //Initialize a Session for the user interface.
        System.out.println(welcomeString());
        System.out.println(commandsString());
        Scanner inputStream = new Scanner(System.in);
        String input = inputStream.nextLine().toLowerCase(Locale.ROOT);

        //Split input into a command keyword (Leading word) and it's parameters (as a String).
        String command = input.split(" ")[0];
        String parameters = input.substring(command.length()).trim();


        //While logout hasn't been called by the user, we're in a session, so we're exchanging output and user input.
        while (!command.equals("logout")) {
            String output = getInterfaceString(command, parameters);
            System.out.println(output);
            System.out.println(commandsString());

            input = inputStream.nextLine().toLowerCase(Locale.ROOT);
            command = input.split(" ")[0];
            parameters = input.substring(command.length()).trim();
        }

    }

    protected String getInterfaceString(String Command, String Parameters) {
        return null;
    }

    protected String welcomeString() {
        return String.format("Welcome %s!", this.fullName);
    }

    protected String commandsString() {
        return "Commands: " + String.join(" ", this.commands) + " logout";
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
        if (!approved) str.append("Not ");
        str.append("Approved");
        return str.toString();
    }


}

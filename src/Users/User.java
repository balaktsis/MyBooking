package Users;
import Misc.Message;
import Misc.UniqueIDGenerator;

/**
 * This is a parent User class, which contains core functionality that will be
 * present in all types of users of the project
 * @author Neron Panagiotopoulos
 */

public class User {

    protected final String uid;
    protected boolean approved;
    protected String username, password;
    protected Message[] messages;


    /**
     * Primary constructor of a User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public User(String username, String password){
        this.uid = UniqueIDGenerator.getUniqueId();
        this.username = username;
        this.password = password;
        this.approved = false;
    }

    /**
     * @return Unique ID assigned to the User object.
     */
    public String getUserId(){
        return this.uid;
    }

    /**
     * Update the username of the current user.
     * @param username New username for the user
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Update the password of the current user.
     * @param password New password for the user
     */
    public void setPassword(String password){
        this.password = password;
    }

    private void updateMessages(){
        //TODO:get list of messages
    }

    public Message[] getMessages(){
        return messages;
    }

}

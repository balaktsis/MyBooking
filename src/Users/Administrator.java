package Users;
import Booking.BookingEntry;
import Misc.Storage;
import java.util.ArrayList;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Administrator User Type
 * @author Neron Panagiotopoulos
 */
public class Administrator extends User{


    /**
     * Primary constructor of an Administrator User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Administrator(String username, String password) {
        super(username, password);
        this.commands = new String[]{"approve_user", "show_bookings", "lookup_booking", "show_users", "lookup_user"};
    }


    @Override
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "approve_user" -> str.append(approveUser(parameters));
            case "show_bookings" -> str.append(showBookings());
            case "lookup_booking" -> str.append(approveUser(parameters)); //TODO
            case "show_users" -> str.append(showUsers());
            case "lookup_user" -> str.append(lookupUser(parameters));
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
    }

    /**
     * Set the approval status of a User to approved
     * @param username of the user that shall be set as approved
     * @return String, result of the operation
     */
    private String approveUser(String username){
        if (username.equals("")){
            StringBuilder returnStr = new StringBuilder();
            returnStr.append("Missing parameter: Username");
            returnStr.append("\n");

            ArrayList<String> unapprovedUsers = new ArrayList<>();
            for (User user: Storage.getUsers()) {
                if (!user.getApprovalStatus()){
                    unapprovedUsers.add(user.username);
                }
            }

            returnStr.append("Users pending approval: ");
            returnStr.append(String.join(" ", unapprovedUsers));

            return returnStr.toString();

        }

        for(User user : Storage.getUsers()){
            if (user.getUsername().equalsIgnoreCase(username)){
                user.setApprovalStatus(true);
                return "User " + username + " approved!";
            }
        }
        return "No user found under the name " + username;
    }

    /**
     * Show all booking entries
     * @return String with all bookings
     */
    private String showBookings(){
        StringBuilder returnStr = new StringBuilder();
        for (BookingEntry booking : Storage.getBookings()) {
            returnStr.append(booking.toString());
            returnStr.append("\n");
        }
        return returnStr.toString();
    }

    /**
     * @return String concatenated with every username in the database.
     */
    private String showUsers(){
        ArrayList<String> users = new ArrayList<>();
        for (User user : Storage.getUsers()) {
            users.add(user.getUsername());
        }
        return String.join(" ", users);
    }

    /**
     * Get the details of a User in the database
     * @param username of the user
     * @return Details of the user/Status if not found
     */
    private String lookupUser(String username){

        if (username.equals("")) return "Missing parameter: Username";

        for (User user : Storage.getUsers()){
            if (user.getUsername().equalsIgnoreCase(username)){
                return user.toString();
            }
        }
        return "No user found under the name " + username;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Administrator\n");
        str.append(super.toString());
        return str.toString();
    }

}

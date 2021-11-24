package Users;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Customer User Type
 * @author
 */
public class Customer extends User{

    private String address;

    /**
     * Primary constructor of a Customer User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Customer(String username, String password) {
        super(username, password);
        this.commands = new String[]{"approve_user", "show_bookings", "lookup_booking", "show_users", "lookup_user"};
    }

    /**
     * Set the user's address
     * @param address String containing a user's address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * @return String containing a user's address
     */
    public String getAddress(){
        return this.address;
    }

    @Override
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "book" -> str.append(bookLodge(parameters));
            case "search" -> {}
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
    }

    private String bookLodge(String LodgeID){
        if (LodgeID.equals("")){
            return "Missing parameter: LodgeID";
        }
        //TODO
        return "TODO";
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Customer\n");
        str.append(super.toString());
        str.append("\nAddress: ");
        str.append(this.address);
        return str.toString();
    }

}

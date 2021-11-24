package Users;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Landlord User Type
 * @author
 */
public class Landlord extends User{

    private String base;

    /**
     * Primary constructor of a Landlord User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Landlord(String username, String password) {
        super(username, password);
        this.commands = new String[]{"approve_user", "show_bookings", "lookup_booking", "show_users", "lookup_user"};
    }

    /**
     * Set the user's address
     * @param base String containing a landlord's base
     */
    public void setBase(String base){
        this.base = base;
    }

    /**
     * @return String containing a landlord's base
     */
    public String getBase(){
        return this.base;
    }

    @Override
    protected String getInterfaceString(String command, String parameters){
        StringBuilder str = new StringBuilder();

        switch (command) {
            case "list" -> str.append(listLodge(parameters));
            case "search" -> {}
            default -> str.append("Unknown command, please try again!");
        }

        return str.toString();
    }

    private String listLodge(String LodgeID){
        if (LodgeID.equals("")){
            return "Missing parameters";
        }
        //TODO
        return "TODO";
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Landlord\n");
        str.append(super.toString());
        str.append("\nBase: ");
        str.append(this.base);
        return str.toString();
    }

}

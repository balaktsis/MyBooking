package Users;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Landlord User Type
 * @author
 */
public class Landlord extends User{
    /**
     * Primary constructor of a Landlord User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Landlord(String username, String password) {
        super(username, password);
    }
}

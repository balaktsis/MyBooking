package Users;

/**
 * This is a child of the User class, which contains functionality unique
 * to the Administrator User Type
 * @author
 */
public class Administrator extends User{
    private static int totalBookings;

    /**
     * Primary constructor of an Administrator User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Administrator(String username, String password) {
        super(username, password);
    }

    public static int getTotalBookings() {
        return totalBookings;
    }

    @Override
    protected void showCommandlineInterface() {
        System.out.println("This is the Administrator interface!");
    }
}

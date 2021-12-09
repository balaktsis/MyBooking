package LoginSystem;

import java.util.ArrayList;

/**
 * This enum consists of a list of different user-roles interacting with the application.
 * @author Christos Balaktsis
 */

public enum Roles {
    ADMINISTRATOR, LANDLORD, CUSTOMER;

    /**
     * @param value A string representing a role.
     * @return if the value is one of the system (enum's) roles.
     */
    public static boolean isRole(String value) {
        ArrayList<String> roles = new ArrayList<>();
        for(Roles role : Roles.values())
            roles.add(role.toString());
        for (String role : roles)
            if(role.equals(value)) return true;
        return false;
    }
}

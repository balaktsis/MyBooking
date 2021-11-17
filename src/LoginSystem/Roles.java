package LoginSystem;

import java.util.ArrayList;

public enum Roles {
    ADMINISTRATOR, LANDLORD, CUSTOMER;

    public static boolean isRole(String value) {
        ArrayList<String> roles = new ArrayList<>();
        for(Roles role : Roles.values())
            roles.add(role.toString());
        for (String role : roles)
            if(role.equals(value)) return true;
        return false;
    }
}

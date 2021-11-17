import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import java.util.ArrayList;
import java.util.List;

public class AppSystem {

    public AppSystem(){
        initializeApp();
    }

    private void initializeApp(){

        ArrayList<User> users = new ArrayList<>();

        var tempAdmin = new Administrator("admin", "password");
        var tempCustomer = new Customer("customer", "password");
        var tempLandlord = new Landlord("landlord", "password");

        tempAdmin.setApprovalStatus(true);

        tempAdmin.setFullName("Admin McAdminFace");
        tempCustomer.setFullName("Karen Managerhunter");
        tempLandlord.setFullName("Rick James");

        users.add(tempAdmin);
        users.add(tempCustomer);
        users.add(tempLandlord);

        //TODO: Add a list/Database thing for the users

    }

}

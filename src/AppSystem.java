import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import Misc.Storage;

import java.util.ArrayList;

public class AppSystem {
    private Storage storage;

    public AppSystem(Storage storage){
        this.storage = storage;
        initializeApp();
    }

    private void initializeApp(){

        ArrayList<User> users = new ArrayList<>();
        var tempAdmin = new Administrator("admin", "password0");
        var tempCustomer = new Customer("customer", "password0");
        var tempLandlord = new Landlord("landlord", "password0");

        tempAdmin.setApprovalStatus(true);

        tempAdmin.setFullName("Admin McAdminFace");
        tempCustomer.setFullName("Karen Managerhunter");
        tempLandlord.setFullName("Rick James");

        users.add(tempAdmin);
        users.add(tempCustomer);
        users.add(tempLandlord);

        tempAdmin.showInterface(false);

        for(User user : users) storage.getRegUsers().add(user);

        //TODO: Add a list/Database thing for the users

    }

}

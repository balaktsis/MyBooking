import LoginSystem.LoginSystem;
import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import Misc.Storage;

import java.util.ArrayList;

public class AppSystem {

    public AppSystem(){
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

        tempAdmin.setApprovalStatus(true);
        tempCustomer.setApprovalStatus(true);
        tempLandlord.setApprovalStatus(true);

        users.add(tempAdmin);
        users.add(tempCustomer);
        users.add(tempLandlord);

        for(User user : users) Storage.getUsers().add(user);

        LoginSystem loginSystem = new LoginSystem(false);
        loginSystem.showLoginScreen();


    }

}

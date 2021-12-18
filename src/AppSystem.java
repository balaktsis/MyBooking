import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Lodge;
import Lodges.LodgeType;
import LoginSystem.LoginSystem;
import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import Misc.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * App System class, initializes the preset fields and controls the visual flow.
 * @author Neron Panagiotopoulos
 * @author Christos Balaktsis
 */

public class AppSystem {

    LoginSystem loginSystem;
    boolean graphical;

    /**
     * Default constructor for the AppSystem class
     */
    public AppSystem(boolean graphical){
        this.graphical = graphical;
        initializeApp();
    }

    /**
     * Initialize constant and temporary fields
     */
    private void initializeApp(){
        loginSystem = new LoginSystem(graphical);
        initializeWithTempFields();
    }

    /**
     * Begin app execution by running the loginScreen
     */
    public void run(){
        loginSystem.showLoginScreen();
    }

    /**
     * Initialize some temporary fields (Users of all types, one lodge and a booking)
     * to demonstrate the app's functionality.
     */
    private void initializeWithTempFields(){
        //Setting up the users
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

        tempLandlord.setBase("Thessaloniki, Greece");

        users.add(tempAdmin);
        users.add(tempCustomer);
        users.add(tempLandlord);

        for(User user : users) {
            Storage.getUsers().add(user);
        }

        //Setting up a preset lodge
        Lodge tempLodge = new Lodge(tempLandlord, "Ethnikis Aminis 41, Thessaloniki 546 35, Greece", LodgeType.APARTMENT);
        tempLodge.getDetails().setTitle("Feels-like-home");
        HashSet<Amenities> amenities = new HashSet<>();
        amenities.add(Amenities.WIFI); amenities.add(Amenities.PARKING);
        tempLodge.setAmenities(amenities);
        tempLodge.getDetails().setPrice(20.0);
        tempLodge.getDetails().setBeds(5);
        tempLodge.getDetails().setDescription("The best place to live!");
        tempLodge.getDetails().setSize(80);
        Storage.getLodges().add(tempLodge);


        BookingEntry tempBooking = new BookingEntry(tempCustomer, tempLodge);
        tempBooking.bookLodge(LocalDate.now(), LocalDate.now().plusDays(10));
        Storage.getBookings().add(tempBooking);
    }

}

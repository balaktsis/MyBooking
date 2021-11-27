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
 *
 * @author Neron Panagiotopoulos
 * @author Christos Balaktsis
 */

public class AppSystem {

    LoginSystem loginSystem;

    /**
     * Default constructor for the AppSystem class
     */
    public AppSystem(){
        initializeApp();
    }

    /**
     * Initialize constant and temporary fields
     */
    private void initializeApp(){
        loginSystem = new LoginSystem(false);
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

        Lodge tempLodge = new Lodge(tempLandlord, "Ethnikis Aminis 41, Thessaloniki 546 35, Greece", LodgeType.APARTMENT);
        tempLodge.setTitle("Feels-like-home");
        HashSet<Amenities> amenities = new HashSet<>();
        amenities.add(Amenities.WIFI); amenities.add(Amenities.PARKING);
        tempLodge.setAmenities(amenities);
        tempLodge.setPrice(20.0);
        tempLodge.setBeds(5);
        tempLodge.setDescription("The best place to live!");
        tempLodge.setSize(80);
        Storage.getLodges().add(tempLodge);


        BookingEntry tempBooking = new BookingEntry(tempCustomer, tempLodge);
        tempBooking.bookLodge(LocalDate.now(), LocalDate.now().plusDays(10));
        Storage.getBookings().add(tempBooking);
    }

}

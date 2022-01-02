package Misc;

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

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * App System class, initializes the preset fields and controls the visual flow.
 * @author Neron Panagiotopoulos
 * @author Christos Balaktsis
 */

public class AppSystem {

    static LoginSystem loginSystem;
    static boolean graphical = true;

    /**
     * Begin app execution by initializing temporary fields and showing the loginScreen
     */
    public static void run(){
        initializeApp();
        getLogin();
//        for (User user : Storage.getUsers()) {
//            if (user.getUsername().equalsIgnoreCase("admin")){
//                user.showInterface(true);
//            }
//        }
    }

    /**
     * Initialize constant and temporary fields
     */
    private static void initializeApp(){
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            // handle exception
        }
//        initializeFromFile();
        initializeWithTempFields();
    }



    /**
     * Initialize some temporary fields (Users of all types, one lodge and a booking)
     * to demonstrate the app's functionality.
     */
    private static void initializeWithTempFields(){
        //Setting up the users
        HashSet<User> users = new HashSet<>();
        var tempAdmin = new Administrator("admin", "password0");
        var tempCustomer = new Customer("customer", "password0");
        var tempLandlord = new Landlord("landlord", "password0");

        tempAdmin.setApprovalStatus(true);

        tempAdmin.setFullName("Admin McAdminFace");
        tempCustomer.setFullName("Karen Managerhunter");
        tempLandlord.setFullName("Rick James");

        tempAdmin.setApprovalStatus(true);
        tempCustomer.setApprovalStatus(false);
        tempLandlord.setApprovalStatus(false);

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
        tempLodge.getDetails().setImage(new ImageIcon("src/Misc/images/tmpLodgeImage.gif"));
        Storage.getLodges().add(tempLodge);

        BookingEntry tempBooking = new BookingEntry(tempCustomer, tempLodge);
        tempBooking.bookLodge(LocalDate.now(), LocalDate.now().plusDays(10));
        Storage.getBookings().add(tempBooking);
    }

    private void initializeFromFile() {
        Storage.drawDataFromFiles();
    }

    public static void exit(){
        Storage.storeDataToFiles();
        System.exit(0);
    }

    //Run the login screen
    public static void getLogin(){
        loginSystem = new LoginSystem(graphical);
        loginSystem.showLoginScreen();
    }

    //Run the user interface of a user
    public static void getUserInterface(User user){
        user.showInterface(graphical);
    }

}

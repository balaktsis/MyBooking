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
    static final boolean graphical = true;

    /**
     * Begin app execution by initializing temporary fields and showing the loginScreen
     */
    public static void run(){
        initializeApp();
        getLogin();
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
            // Fall back to the default UI
        }
        initializeFromFile();
    }

    private static void initializeFromFile() {
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

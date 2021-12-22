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
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

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
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            // handle exception
        }
        loginSystem = new LoginSystem(graphical);
        initializeFromFile();
    }

    /**
     * Begin app execution by running the loginScreen
     */
    public void run(){
        loginSystem.showLoginScreen();
//        for (User user : Storage.getUsers()) {
//            if (user.getUsername().toLowerCase(Locale.ROOT).equals("admin")){
//                user.showInterface(true);
//            }
//        }
    }

    /**
     * Initialize some temporary fields (Users of all types, one lodge and a booking)
     * to demonstrate the app's functionality.
     */
    private void initializeWithTempFields(){
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

        HashSet<BookingEntry> bookingEntries = new HashSet<>();
        bookingEntries.add(tempBooking);

        HashSet<Lodge> lodges = new HashSet<>();
        lodges.add(tempLodge);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storedUsers.dat"))) {
            oos.writeObject(users);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storedLodges.dat"))) {
            oos.writeObject(lodges);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storedBookings.dat"))) {
            oos.writeObject(bookingEntries);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initializeFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storedUsers.dat"))) {
            Storage.setUsers((HashSet<User>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Storage.setUsers(new HashSet<>());
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storedLodges.dat"))) {
            Storage.setLodges((HashSet<Lodge>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Storage.setLodges(new HashSet<>());
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storedBookings.dat"))) {
            Storage.setBookings((HashSet<BookingEntry>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Storage.setBookings(new HashSet<>());
        }
    }

}

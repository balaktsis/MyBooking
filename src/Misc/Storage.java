package Misc;

import Booking.BookingEntry;
import Lodges.Lodge;
import Users.*;

import java.io.*;
import java.util.HashSet;

/**
 * Class that contains all the data collections that are necessary for the application execution and operations.
 * @author Christos Balaktsis
 * @author Neron Panagiotopoulos
 */

public class Storage {

    private static HashSet<User> users = new HashSet<>();

    private static HashSet<Lodge> lodges = new HashSet<>();

    private static HashSet<BookingEntry> bookings = new HashSet<>();

    /**
     * Connects the HashSet of Users to a .dat file that contains such info.
     * @param usersFile The .dat file where a HashSet of Users is stored.
     */
    public static void setUsers(HashSet<User> usersFile) {
        users = usersFile;
    }

    /**
     * Connects the HashSet of Lodges to a .dat file that contains such info.
     * @param lodgesFile The .dat file where a HashSet of Lodges is stored.
     */
    public static void setLodges(HashSet<Lodge> lodgesFile) {
        lodges = lodgesFile;
    }

    /**
     * Connects the HashSet of Booking Entries to a .dat file that contains such info.
     * @param bookingsFile The .dat file where a HashSet of Booking Entries is stored.
     */
    public static void setBookings(HashSet<BookingEntry> bookingsFile) {
        bookings = bookingsFile;
    }

    /**
     * @return A HashSet of all the registered users in the system.
     */
    public static HashSet<User> getUsers() {
        return users;
    }

    /**
     * @return A HashSet of all the registered lodges in the system.
     */
    public static HashSet<Lodge> getLodges() {
        return lodges;
    }

    /**
     * @return A HashSet of all the registered bookings in the system.
     */
    public static HashSet<BookingEntry> getBookings() {
        return bookings;
    }

    /**
     * Stores all the application data on .dat files.
     */
    public static void storeDataToFiles() {
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
            oos.writeObject(bookings);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the stored data sets from a file and connects them with the runtime Storage fields.
     */
    public static void drawDataFromFiles() {
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

package Misc;

import Booking.BookingEntry;
import Lodges.Lodge;
import Users.*;

import java.util.HashSet;

/**
 * Class that contains all the data collections that are necessary for the application execution and operations.
 * @author Christos Balaktsis
 * @author Neron Panagiotopoulos
 */

public class Storage {

    private static final HashSet<User> users = new HashSet<>();

    private static final HashSet<Lodge> lodges = new HashSet<>();

    private static final HashSet<BookingEntry> bookings = new HashSet<>();

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

}

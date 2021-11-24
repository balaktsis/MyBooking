package Misc;

import Booking.BookingEntry;
import Lodges.Lodge;
import Users.*;

import java.util.HashSet;

public class Storage {

    private static final HashSet<User> users = new HashSet<>();
    private static final HashSet<Lodge> lodges = new HashSet<>();

    private static final HashSet<BookingEntry> bookings = new HashSet<>();


    public static HashSet<User> getUsers() {
        return users;
    }

    public static HashSet<Lodge> getLodges() {
        return lodges;
    }

    public static HashSet<BookingEntry> getBookings() {
        return bookings;
    }

}

package Misc;

import Booking.BookingEntry;
import Lodges.Lodge;
import Users.*;

import java.util.HashSet;

public class Storage {
    //TODO I have somewhat of an idea what I'm doing :/

    private static final HashSet<User> regUsers = new HashSet<>();
    private static final HashSet<Lodge> lodges = new HashSet<>();

    private static final HashSet<BookingEntry> bookings = new HashSet<>();


    public static HashSet<User> getRegUsers() {
        return regUsers;
    }

    public static HashSet<Lodge> getLodges() {
        return lodges;
    }

    public static HashSet<BookingEntry> getBookings() {
        return bookings;
    }

}

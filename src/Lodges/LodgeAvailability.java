package Lodges;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * This is a Lodge Availability class that represents booking calendar. It consists of a collection of dates,
 * on which a lodge is already booked.
 * @author Christos Balaktsis
 */

public class LodgeAvailability {
    HashSet<LocalDate> bookCalendar;

    /**
     * Default constructor that initializes the HashSet collection of booked-days.
     */
    public LodgeAvailability() {
        bookCalendar = new HashSet<>();
    }

    /**
     * @return the HashSet of booked dates of a lodge.
     */
    public HashSet<LocalDate> getBookCalendar() {
        return this.bookCalendar;
    }

    /**
     * Updates the booking calendar of a lodge.
     * @param bookCalendar The HashSet of Dates marked as booked for a lodge.
     */
    public void setBookCalendar(HashSet<LocalDate> bookCalendar) {
        this.bookCalendar = bookCalendar;
    }

    /**
     * Method that checks if some dates are available for booking or not (so, already rented).
     * @param dates A set of dates requested for booking.
     * @return if all the dates are available for booking.
     */
    public boolean markDates(HashSet<LocalDate> dates) {
        if(dates == null) return false;
        for (LocalDate date : dates)
            if (bookCalendar.contains(date)) return false;
        bookCalendar.addAll(dates);
        return true;
    }

    /**
     * Method that sets some dates available again for booking (abolishing them from the calendar).
     * @param dates A list of dates asked for abolition.
     */
    public void freeDates(HashSet<LocalDate> dates) {
        if(dates != null)
            for (LocalDate date : dates)
                bookCalendar.remove(date);
    }

}

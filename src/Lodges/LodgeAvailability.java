package Lodges;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * This is a Lodge Availability class that represents booking calendar. It consists of a collection of dates,
 * on which a lodge is already booked.
 * @author Christos Balaktsis
 */

public class LodgeAvailability {
    HashSet<Date> bookCalendar;

    /**
     * Default constructor that initializes the HashSet collection of booked-days.
     */
    public LodgeAvailability() {
        bookCalendar = new HashSet<>();
    }

    /**
     * @return the HashSet of booked dates of a lodge.
     */
    public HashSet<Date> getBookCalendar() {
        return this.bookCalendar;
    }

    /**
     * Updates the booking calendar of a lodge.
     * @param bookCalendar The HashSet of Dates marked as booked for a lodge.
     */
    public void setBookCalendar(HashSet<Date> bookCalendar) {
        this.bookCalendar = bookCalendar;
    }

    /**
     * Method that checks if some dates are available for booking or not (so, already rented).
     * @param dates A list of dates requested for booking.
     * @return if all the dates are available for booking.
     */
    public boolean markDates(Date[] dates) {
        if(dates == null) return false;
        for (Date date : dates)
            if (bookCalendar.contains(date)) return false;
        bookCalendar.addAll(Arrays.asList(dates));
        return true;
    }

    /**
     * Method that sets some dates available again for booking (abolishing them from the calendar).
     * @param dates A list of dates asked for abolition.
     */
    public void freeDates(Date[] dates) {
        if(dates != null)
            for (Date date : dates)
                bookCalendar.remove(date);
    }

}

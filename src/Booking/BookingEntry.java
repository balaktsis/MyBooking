package Booking;

import Lodges.Lodge;
import Users.Customer;

import static Misc.UniqueIDGenerator.getUniqueId;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * This is a Booking Entry class that builds a reservation of a customer to a specific lodge.
 * @author Christos Balaktsis
 */

public class BookingEntry {
    private final String bookingId;
    private double totalCost;
    private final Lodge lodge;
    private final Customer tenant;
    private final HashSet<LocalDate> period;
    private boolean valid;
    private final LocalDate entryDate;
    private LocalDate arrivalDate;
    private LocalDate departureDate;

    /**
     * Default constructor of class. Sets the user (type Customer) that asks for booking a specific lodge.
     * @param tenant The customer that applies for reservation.
     * @param lodge The requested lodge for reservation.
     */
    public BookingEntry(Customer tenant, Lodge lodge) {
        this.tenant = tenant;
        this.lodge = lodge;
        this.totalCost = 0;
        this.period = new HashSet<>();
        this.valid = false;
        this.entryDate = LocalDate.now();
        this.bookingId = getUniqueId();
    }

    /**
     * Method that creates a period of dates (from date1 to date2) and adds each date in a HashSet.
     * @param date1 The first date of the period.
     * @param date2 The last date of the period (not included in the HashSet).
     * @return A HashSet of Dates, the period between date1 and date2.
     */
    private HashSet<LocalDate> getPeriod(LocalDate date1, LocalDate date2) {
        while(date1.isBefore(date2)) {
            this.period.add(date1);
            date1 = date1.plusDays(1);
        }
        return this.period;
    }

    /**
     * Method for requesting booking a Lodge.
     * @param arrival The date of requested arrival in the lodge.
     * @param departure The date of requested departure from the lodge.
     * @return If the lodge is available on that period and the requested booking got approved.
     */
    public boolean bookLodge(LocalDate arrival, LocalDate departure) {
        this.arrivalDate = arrival;
        this.departureDate = departure;
        if(arrival.isAfter(departure) || arrival.isEqual(departure)) return false;
        if(lodge.bookLodge(getPeriod(arrival,departure))) {
            this.totalCost = this.lodge.getPrice() * this.period.size();
            this.valid = true;
            return true;
        }
        return false;
    }

    /**
     * @return The date the booking entry was created.
     */
    public LocalDate getEntryDate() {
        return this.entryDate;
    }

    /**
     * @return If the booking entry is valid. Else it got canceled.
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * @return The total cost for accommodation at the booked lodge.
     */
    public double getTotalCost() {
        return this.valid ? this.totalCost : 0;
    }

    /**
     * @return The user (type Customer) requested to create a booking.
     */
    public Customer getTenant() {
        return this.tenant;
    }

    /**
     * @return The requested period of accommodation (booking).
     */
    public HashSet<LocalDate> getPeriod() {
        return this.valid ? this.period : null;
    }

    /**
     * @return The booked lodge.
     */
    public Lodge getLodge() {
        return this.lodge;
    }

    /**
     * Method that cancels the booking entry, updating the availability dates of the current lodge.
     * @return If the cancellation is completed. Else the booking was invalid.
     */
    public boolean cancelBooking() {
        if(this.valid) {
            this.lodge.setAvailableDates(this.period);
            this.valid = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BookingEntry bookingEntry))
            return false;
        return this.lodge == bookingEntry.lodge && this.tenant.equals(bookingEntry.tenant) && this.period.equals(bookingEntry.period);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (this.lodge != null ? this.lodge.hashCode() : 0);
        hash = 31 * hash + (this.tenant != null ? this.tenant.hashCode() : 0);
        hash = 31 * hash + (this.period != null ? this.period.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Booking Entry #");
        str.append(this.bookingId);
        str.append("\n");
        str.append("Booked lodge:\t");
        str.append(this.lodge.getTitle());
        str.append("\tTenant:\t");
        str.append(this.tenant.getUsername());
        str.append("\nPeriod of reservation:\t");
        str.append(this.arrivalDate.toString());
        str.append("-");
        str.append(this.departureDate.toString());
        str.append("\tTotal Cost for accommodation:\t");
        str.append(this.totalCost);
        str.append("\n");
        return valid ? str.toString() : "Invalid Booking Entry\n";
    }

}

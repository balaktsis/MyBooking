package Users.Actions.Admin;

import Booking.BookingEntry;
import Users.Actions.Command;
import Users.User;

import java.util.ArrayList;
import java.util.List;

public class BookingsLookup implements Command {
    @Override
    public String getCommandName() {
        return "lookup_bookings";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " date\neg: " + getCommandName() + " 2021-12-03";
    }

    @Override
    public int getMaxParams() {
        return 1;
    }

    @Override
    public int getMinParams() {
        return 0;
    }

    /**
     * Look up all bookings that were made on a given date
     * @param args Booking Date String of the booking date in iso 8086 format (YYYY-MM-DD)
     * @return String containing all bookings made on the given date
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        List<BookingEntry> foundBookings;

        if (args.size() == 0){
            foundBookings = BookingEntry.getBookingsOnDate(null);
        } else {
            foundBookings = BookingEntry.getBookingsOnDate(args.get(0));
        }

        if (foundBookings.size()==0){
            return "No bookings were found!";
        }

        for (BookingEntry bookingEntry : foundBookings){
            returnStr.append(bookingEntry).append("\n");
        }

        return returnStr.toString();
    }
}

package Users.Actions.CommandLine.Landlord;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.List;

public class BookingLookup implements Command {
    @Override
    public String getCommandName() {
        return "lookup_booking";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " bookingId";
    }

    @Override
    public int getMaxParams() {
        return 1;
    }

    @Override
    public int getMinParams() {
        return 1;
    }

    /**
     * @param args The ID of a booking entry.
     * @return The booking entry details.
     */
    @Override
    public String run(User user, List<String> args) {
        String bookingId = args.get(0);
        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getBookingId().equals(bookingId) && bookingEntry.getLodge().getLandlord().equals(user)) {
                returnStr.append(bookingEntry).append("\n");
                break;
            }
        return returnStr.toString().equals("") ? "The requested booking entry does not exist or does not apply to any of your properties." : returnStr.toString();
    }
}

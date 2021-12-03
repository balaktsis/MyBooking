package Users.Actions.Landlord;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.Command;
import Users.User;

import java.util.List;

public class CancelBooking implements Command {
    @Override
    public String getCommandName() {
        return "cancel_booking";
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
     * @return Message string about the cancellation process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        String bookingId = args.get(0);
        StringBuilder returnStr = new StringBuilder();
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getBookingId().equals(bookingId)) {
                if(bookingEntry.getLodge().getLandlord().equals(user)) {
                    if(bookingEntry.cancelBooking())
                        returnStr.append("Cancellation completed. Your lodge is now available on period ")
                                .append(bookingEntry.getArrivalDate()).append(" - ")
                                .append(bookingEntry.getDepartureDate()).append(".");
                    else
                        returnStr.append("Invalid booking entry. Cancellation failed.");
                    break;
                }
                else
                    return "The requested booking entry does not apply to any of your properties.";
            }
        return returnStr.toString().equals("") ? "The requested booking entry does not exist." : returnStr.toString();
    }
}

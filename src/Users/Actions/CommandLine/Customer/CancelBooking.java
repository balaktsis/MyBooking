package Users.Actions.CommandLine.Customer;

import Booking.BookingEntry;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
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
     * Cancel a booking
     * @param args String, ID of the booking to be canceled
     * @return Result of the operation
     */
    @Override
    public String run(User user, List<String> args) {
        String bookingID = args.get(0);
        for (BookingEntry booking : Storage.getBookings()){
            if (booking.getBookingId().equals(bookingID)){
                booking.cancelBooking();
                return "Canceled booking with ID: " + bookingID;
            }
        }
        return "No bookings were found for ID: " + bookingID;
    }
}

package Users.Actions.CommandLine.Landlord;

import Booking.BookingEntry;
import Lodges.Hotel;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.List;
import java.util.Objects;

public class DeleteLodge implements Command {
    @Override
    public String getCommandName() {
        return "delete_lodge";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " lodgeId";
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
     * Removes a lodge of landlords property, if existed.
     *
     * @param args The ID of the lodge.
     * @return Message string about the deletion process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        String lodgeId = args.get(0);

        Lodge lodge = null;
        boolean removed = false, lodgeExists = false;

        //Find the requested-for-deletion lodge.
        for (Lodge tempLodge : Storage.getLodges())
            if (Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(user)) {
                lodge = tempLodge;
                lodgeExists = true;
                break;
            }
        if (!lodgeExists) return "Lodge #" + lodgeId + " is not under your property or does not exist.";

        //If the requested lodge is a room, the room list of the hotel, it belongs to, has to be updated.
        if (lodge.getType().equals(LodgeType.ROOM)) {
            for (Lodge tempLodge : Storage.getLodges()) {
                if (Objects.equals(tempLodge.getType(), LodgeType.HOTEL)) {
                    Hotel hotel = (Hotel) tempLodge;
                    if (hotel.getRooms().containsKey(lodgeId)) {
                        removed = hotel.removeRoom(lodgeId);
                        break;
                    }
                }
            }
        } else {
            //If the requested lodge is a hotel, all the rooms of the hotel have to be removed both from the hotel's
            //room list and the system's storage.
            if (lodge.getType().equals(LodgeType.HOTEL)) {
                Hotel hotel = (Hotel) lodge;
                for (String room : hotel.getRooms().keySet())
                    hotel.removeRoom(room);
            }
            removed = Storage.getLodges().remove(lodge);
        }
        if(removed)
            for(BookingEntry bookingEntry : Storage.getBookings())
                if(bookingEntry.getLodge().equals(lodge)) bookingEntry.cancelBooking();
        return removed ? "Lodge has been removed!" : "Unable to remove the requested lodge!";
    }
}

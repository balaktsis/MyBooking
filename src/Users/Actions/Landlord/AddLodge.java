package Users.Actions.Landlord;

import Lodges.Amenities;
import Lodges.Hotel;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Users.Actions.Command;
import Users.Landlord;
import Users.User;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AddLodge implements Command {
    @Override
    public String getCommandName() {
        return "add_lodge";
    }

    @Override
    public String getUsage() {
        return getCommandName();
    }

    @Override
    public int getMaxParams() {
        return 0;
    }

    @Override
    public int getMinParams() {
        return 0;
    }


    /**
     * Creation of a new lodge belonging to the current landlord.
     *
     * @return Message string about the creation process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        Scanner input = new Scanner(System.in);
        String type, answer;
        Lodge newLodge, requestedHotel = null;

        LodgeType lodgeType;

        System.out.println("In order to add a new lodge for bookings, please complete the following fields...");

        do {
            System.out.print("Type (" + LodgeType.getLodgeTypes() + "): ");
            type = input.nextLine().toUpperCase(Locale.ROOT);
            if (!LodgeType.isLodgeType(type)) System.out.println("Unknown type of Lodge. Try something else.");
        } while (!LodgeType.isLodgeType(type));
        lodgeType = LodgeType.valueOf(type);

        if(lodgeType==LodgeType.ROOM) {
            System.out.println("Please provide the ID of your hotel you want to add the room at.");
            String hotelId = input.nextLine();
            for (Lodge lodge : Storage.getLodges()) {
                if (lodge.getLodgeId().equals(hotelId) && lodge.getType() == LodgeType.HOTEL) {
                    requestedHotel = lodge;
                    break;
                }
            }
            if (requestedHotel == null) return "No hotel with Id " + hotelId + " was found.";
            newLodge = new Lodge((Landlord) user, requestedHotel.getDetails().getLocation(), LodgeType.ROOM);

        } else {
            System.out.print("Location: ");
            String location = input.nextLine();

            if (lodgeType.equals(LodgeType.HOTEL))
                newLodge = new Hotel((Landlord) user, location);
            else
                newLodge = new Lodge((Landlord) user, location, lodgeType);
        }

        System.out.print("Title: ");
        newLodge.getDetails().setTitle(input.nextLine());

        System.out.print("Description: ");
        answer = input.nextLine();
        newLodge.getDetails().setDescription(answer);

        if(!newLodge.getType().equals(LodgeType.HOTEL)) {
            System.out.print("Price (per night): € ");
            newLodge.getDetails().setPrice(input.nextDouble());

            System.out.print("Size: (m2) ");
            newLodge.getDetails().setSize(input.nextInt());

            System.out.print("Beds: ");
            newLodge.getDetails().setBeds(input.nextInt());

            System.out.println("Amenities: (type \"yes\" or \"no\" for each one)");
            for (Amenities amenity : Amenities.values()) {
                do {
                    System.out.print(amenity.toString() + ": ");
                    answer = input.nextLine().toLowerCase(Locale.ROOT);
                    if (answer.equals("yes")) {
                        newLodge.addAmenity(amenity);
                        break;
                    } else if (answer.equals("no"))
                        break;
                    System.out.println("Unknown answer. Type \"yes\" or \"no\".");
                } while (true);
            }
        }

        System.out.println("Are you sure you want to add the following " + newLodge.getType().toString() + " to your lodges list?");
        System.out.println("------ " + newLodge);
        do {
            System.out.println("Type \"yes\" or \"no\".");
            answer = input.nextLine().toLowerCase(Locale.ROOT);
            if (answer.equals("yes")) {
                if(newLodge.getType().equals(LodgeType.ROOM)) {
                    System.out.println("* Do not forget to add rooms to your hotel!");
                    Hotel hotel = (Hotel) requestedHotel;
                    hotel.addRoom(newLodge);
                } else Storage.getLodges().add(newLodge);
                return "Lodge #" + newLodge.getLodgeId() + " is added to your lodges!";
            } else if (answer.equals("no"))
                return "Lodge addition cancelled!";
            System.out.println("Unknown answer. Type \"yes\" or \"no\".");
        } while (true);
    }
}

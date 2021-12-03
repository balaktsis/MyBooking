package Users.Actions.Landlord;

import Lodges.Amenities;
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
     * @return Message string about the creation process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        Scanner input = new Scanner(System.in);
        String location, type, answer;
        LodgeType lodgeType;

        System.out.println("In order to add a new Lodge for bookings, please, complete the following fields...");

        System.out.print("Location: ");
        location = input.nextLine();

        do {
            System.out.print("Type (" + LodgeType.getLodgeTypes() + "): ");
            type = input.nextLine().toUpperCase(Locale.ROOT);
            if (!LodgeType.isLodgeType(type)) System.out.println("Unknown type of Lodge. Try something else.");
        } while (!LodgeType.isLodgeType(type));
        lodgeType = LodgeType.valueOf(type);

        Lodge newLodge = new Lodge((Landlord) user, location, lodgeType);

        System.out.print("Title: ");
        newLodge.setTitle(input.nextLine());

        System.out.print("Price (per night): € ");
        newLodge.setPrice(input.nextDouble());

        System.out.print("Size: (m2) ");
        newLodge.setSize(input.nextInt());

        System.out.print("Beds: ");
        newLodge.setBeds(input.nextInt());

        System.out.print("Description: ");
        String dummy = input.nextLine();
        answer = input.nextLine();
        newLodge.setDescription(answer);

        System.out.println("Amenities: (type \"yes\" or \"no\" for each one)");
        for(Amenities amenity : Amenities.values()) {
            do {
                System.out.print(amenity.toString() + ": ");
                answer = input.nextLine().toLowerCase(Locale.ROOT);
                if (answer.equals("yes")) {
                    newLodge.addAmenity(amenity);
                    break;
                } else if (answer.equals("no"))
                    break;
                System.out.println("Unknown answer. Type \"yes\" or \"no\".");
            } while(true);
        }

        System.out.println("Are you sure you want to add the following lodge to your lodges list?");
        System.out.println("------ " + newLodge);
        do {
            System.out.println("Type \"yes\" or \"no\".");
            answer = input.nextLine().toLowerCase(Locale.ROOT);
            if (answer.equals("yes")) {
                Storage.getLodges().add(newLodge);
                return "Lodge #" + newLodge.getLodgeId() + " is added to your lodges!";
            } else if (answer.equals("no"))
                return "Lodge addition cancelled!";
            System.out.println("Unknown answer. Type \"yes\" or \"no\".");
        } while(true);
    }
}

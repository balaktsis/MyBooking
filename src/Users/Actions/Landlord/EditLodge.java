package Users.Actions.Landlord;

import Lodges.Amenities;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.Command;
import Users.Landlord;
import Users.User;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class EditLodge implements Command {
    @Override
    public String getCommandName() {
        return " edit_lodge";
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
     * Edits details of an owned lodge.
     * @param args The ID of the lodge.
     * @return Message string about the editing process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        String lodgeId = args.get(0);
        Lodge lodge = null;
        boolean lodgeExists = false, doneExists = false;
        for(Lodge tempLodge : Storage.getLodges())
            if(Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(user)) {
                lodge = tempLodge;
                lodgeExists = true;
                break;
            }
        if(!lodgeExists) return "Lodge #" + lodgeId + " is not under your property or does not exist.";

        String options = "title description beds size price amenities done ";

        String newString;
        int newInt;
        double newDouble;

        System.out.print("Lodge found. Choose all properties you would like to edit or \"done\" to exit edit mode.\nCommands: ");
        System.out.println(options);

        Scanner inputStream = new Scanner(System.in);
        String input = inputStream.nextLine().toLowerCase(Locale.ROOT);

        String[] fields = input.split(" ");

        for(int j = 0; j < fields.length; j++) {
            if(!options.contains(fields[j] + " ")) {
                System.out.println("Unknown commands (\"" + fields[j] +"\"). Commands: " + options);
                input = inputStream.nextLine().toLowerCase(Locale.ROOT);
                fields = input.split(" ");
                j = -1;
            }
        }

        for (String field : fields)
            if (field.equals("done")) {
                doneExists = true;
                break;
            }

        while (!Objects.equals(fields[0], "done")) {
            for (int i = 0; i < fields.length; i++) {
                switch (fields[i]) {
                    case "title" -> {
                        System.out.print("New title: ");
                        newString = inputStream.nextLine();
                        lodge.setTitle(newString);
                    }
                    case "description" -> {
                        System.out.print("New description: ");
                        newString = inputStream.nextLine();
                        lodge.setDescription(newString);
                    }
                    case "beds" -> {
                        System.out.print("New number of beds: ");
                        newInt = inputStream.nextInt();
                        lodge.setBeds(newInt);
                    }
                    case "size" -> {
                        System.out.print("New size of lodge (in m2): ");
                        newInt = inputStream.nextInt();
                        lodge.setSize(newInt);
                    }
                    case "price" -> {
                        System.out.print("New price per night: € ");
                        newDouble = inputStream.nextDouble();
                        lodge.setPrice(newDouble);
                    }
                    case "amenities" -> {
                        System.out.println("Choose all amenities offered: (type \"yes\" or \"no\" for each one)");
                        for (Amenities amenity : Amenities.values()) {
                            do {
                                System.out.print(amenity.toString() + ": ");
                                newString = inputStream.nextLine().toLowerCase(Locale.ROOT);
                                if (newString.equals("yes")) {
                                    lodge.addAmenity(amenity);
                                    break;
                                } else if (newString.equals("no")) {
                                    if(lodge.getAmenities().contains(amenity))
                                        lodge.removeAmenity(amenity);
                                    break;
                                }
                                System.out.println("Unknown answer. Type \"yes\" or \"no\".");
                            } while (true);
                        }
                    }
                    case "done" -> fields[0] = "done";
                }
                if(i == fields.length - 1 && !doneExists) fields[0] = "done";
            }
        }
        return "Lodge has been updated!";
    }
}

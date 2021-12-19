package Users.Actions.CommandLine.Customer;

import Lodges.Amenities;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LodgesLookup implements Command {
    @Override
    public String getCommandName() {
        return "lookup_lodges";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " criteria (i.e. lookup_lodges wifi parking)";
    }

    @Override
    public int getMaxParams() {
        return Amenities.values().length;
    }

    @Override
    public int getMinParams() {
        return 0;
    }

    /**
     * Parameter Search through lodge options, based on criteria on offered amenities.
     * @param args Input by the user containing all necessary amenities
     * @return String , resulting lodges that fit the criteria
     */
    @Override
    public String run(User user, List<String> args) {
        ArrayList<String> required_amenities = new ArrayList<>(args);

        StringBuilder returnStr = new StringBuilder();

        //Looping through the registered lodges to get the ones matching the criteria.
        //If no criteria set, it returns all lodges
        for (Lodge lodge : Storage.getLodges()){

            //For every lodge, get all it's amenities
            ArrayList<String> available_amenities = new ArrayList<>();
            for (Amenities amenity : lodge.getAmenities()) {
                available_amenities.add(amenity.toString().toLowerCase(Locale.ROOT));
            }

            //If the required amenities are a subset of the available amenities, the lodge is displayed.
            if(available_amenities.containsAll(required_amenities)){
                returnStr.append(lodge).append("\n");
            }
        }

        if (returnStr.isEmpty()) returnStr.append("No lodges were found that satisfy your criteria.");

        return returnStr.toString();
    }
}

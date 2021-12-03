package Users.Actions.Landlord;

import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.Command;
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
     * @param args The ID of the lodge.
     * @return Message string about the deletion process (successful/unsuccessful).
     */
    @Override
    public String run(User user, List<String> args) {
        String lodgeId = args.get(0);
        Lodge lodge = null;
        boolean lodgeExists = false;
        for(Lodge tempLodge : Storage.getLodges())
            if(Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(user)) {
                lodge = tempLodge;
                lodgeExists = true;
                break;
            }
        if(!lodgeExists) return "Lodge #" + lodgeId + " is not under your property or does not exist.";

        return Storage.getLodges().remove(lodge) ? "Lodge has been removed!" : "Unable to remove the requested lodge!";
    }
}

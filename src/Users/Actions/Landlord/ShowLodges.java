package Users.Actions.Landlord;

import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.Command;
import Users.User;
import java.util.List;

public class ShowLodges implements Command {
    @Override
    public String getCommandName() {
        return "show_lodges";
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
     * @return List of all lodges belonging to the current landlord.
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        for (Lodge lodge : Storage.getLodges()) {
            if(lodge.getLandlord().equals(user)) {
                returnStr.append(lodge);
                returnStr.append("\n\n");
            }
        }
        return returnStr.toString().equals("") ? "There are no lodges belonging to you." : returnStr.toString();
    }
}

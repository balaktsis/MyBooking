package Users.Actions.CommandLine.Admin;

import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.CommandLine.Command;
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
     * Get a string containing all registered Lodges
     * @return String
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        for (Lodge lodge : Storage.getLodges()) {
            returnStr.append(lodge.toString()).append("\n\n");
        }
        return returnStr.toString();
    }
}

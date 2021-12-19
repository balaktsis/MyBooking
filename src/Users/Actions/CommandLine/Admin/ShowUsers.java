package Users.Actions.CommandLine.Admin;

import Misc.Storage;
import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.List;

public class ShowUsers implements Command {
    @Override
    public String getCommandName() {
        return "show_users";
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
     * Show all booking entries
     * @return String with all bookings
     */
    @Override
    public String run(User user, List<String> args) {
        StringBuilder returnStr = new StringBuilder();
        for (User user1 : Storage.getUsers()) {
            returnStr.append(user1.toString());
            returnStr.append("\n\n");
        }
        return returnStr.toString();
    }
}

package Users.Actions.CommandLine.Landlord;

import Users.Actions.CommandLine.Command;
import Users.User;

import java.util.List;

public class ShowDetails implements Command {
    @Override
    public String getCommandName() {
        return "my_profile";
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
     * @return The Landlord-user's details.
     */
    @Override
    public String run(User user, List<String> args) {
        return user.toString();
    }
}

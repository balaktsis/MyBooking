package Users.Actions.Admin;

import Users.Actions.Command;
import Users.User;

import java.util.List;

public class UserLookup implements Command {
    @Override
    public String getCommandName() {
        return "lookup_user";
    }

    @Override
    public String getUsage() {
        return getCommandName() + " username";
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
     * Get the details of a User in the database
     * @param args username of the user
     * @return Details of the user/Status if not found
     */
    @Override
    public String run(User user, List<String> args) {

        User searched_user = User.getUserFromUsername(args.get(0));
        if (searched_user == null){
            return "No user found under the name " + args.get(0);
        }
        return searched_user.toString();

    }
}

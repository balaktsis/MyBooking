package Users.Actions.CommandLine.Admin;
import Users.Actions.CommandLine.Command;
import Users.User;
import java.util.List;


public class ApproveUser implements Command {

    @Override
    public String getCommandName() {
        return "approve_user";
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
        return 0;
    }

    /**
     * Set the approval status of a User to approved
     * @param args username of the user that shall be set as approved
     * @return String, result of the operation
     */
    @Override
    public String run(User user, List<String> args) {
        if (args.size() == 0){
            StringBuilder returnStr = new StringBuilder();
            for (User unapprovedUser : User.getUsersWithApproval(false)){
                returnStr.append(unapprovedUser.toString()).append("\n");
            }
            return returnStr.toString();
        }

        User userToBeApproved = User.getUserFromUsername(args.get(0));
        if (userToBeApproved == null){
            return "No user found under that username!";
        }
        userToBeApproved.setApprovalStatus(true);
        return "The user has been approved!";
    }
}
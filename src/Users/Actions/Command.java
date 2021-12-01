package Users.Actions;
import Users.User;
import java.util.List;

public interface Command {

    String getCommandName();
    String getUsage();
    int getMaxParams();
    int getMinParams();
    String run(User user, List<String> args);

}

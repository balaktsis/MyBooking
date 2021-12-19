package Users.Actions.CommandLine;
import Users.User;
import java.util.List;

/**
 * Command interface, contains fields common to all commands which are configured upon implementation
 * and also contains a run command, for invoking the corresponding command
 */
public interface Command {

    String getCommandName();
    String getUsage();
    int getMaxParams();
    int getMinParams();
    String run(User user, List<String> args);

}

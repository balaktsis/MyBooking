package Users.Actions;

import Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public abstract class CommandLineManager {

    protected User parentUser;
    protected Command[] commandsList;

    public CommandLineManager(User parentUser){
        this.parentUser = parentUser;
    }

    /**
     * Begin a command-line user interface session and run the appropriate method for each command input by the user.
     */
    public void showCommandlineInterface() {
        //Initialize a Session for the user interface.
        System.out.println(welcomeString());
        System.out.println(commandsString());
        Scanner inputStream = new Scanner(System.in);
        String input = inputStream.nextLine().toLowerCase(Locale.ROOT);

        String result = parse(input);

        //While logout hasn't been called by the user, we're in a session, so we're exchanging output and user input.
        while (!result.equals("logout")) {
            String output = result;
            System.out.println(output);
            System.out.println(commandsString());

            input = inputStream.nextLine().toLowerCase(Locale.ROOT);
            result = parse(input);
        }

    }

    private String parse(String input){
        //Split input into a command keyword (Leading word) and it's parameters (as a String).
        String commandName = input.split(" ")[0];
        List<String> arguments = List.of(input.substring(commandName.length()).trim().split(" +"));

        Command command = null;
        for (Command commandCandidate : commandsList){
            if (commandCandidate.getCommandName().equals(commandName)){
                command = commandCandidate;
                break;
            }
        }

        if (command == null){
            return "Unknown command! Please try again!";
        }

        if (arguments.size() > command.getMaxParams()){
            return "Too many arguments!\nExpected: " + command.getUsage();
        }

        if (arguments.size() < command.getMinParams()){
            return "Too few arguments!\nExpected: " + command.getUsage();
        }

        return command.run(parentUser, arguments);

    }

    protected String welcomeString() {
        return String.format("Welcome %s!", parentUser.getFullName());
    }

    protected String commandsString() {
        List<String> commands = new ArrayList<>();
        for (Command command : this.commandsList){
            commands.add(command.getCommandName());
        }
        return "Commands: " + String.join(" ", commands) + " logout";
    }

}

package LoginSystem;


import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class LoginSystem {
    private static final String frame = "===============================";

    private static int welcomeMessageConsole() {
        System.out.println(frame);
        System.out.println("Welcome to MyBooking!");
        System.out.println(frame);
        System.out.print("Press [1] to create a new account or [2] to sign in: ");
        return getChoice();
    }

    private static void invalidInput() {
        System.out.println("****\nInvalid input! Please, try again.\n****");
    }

    private static User registerUserConsole() {
        Scanner input = new Scanner(System.in);
        String username, password, reqRole;
        User user;
        System.out.println("\nPlease, provide the following information and your account will be activated soon!");

        System.out.print("Username: ");
        username = input.nextLine();

        password = PasswordField.readPassword("Password: ");

        System.out.print("Address: ");
        ///  user.setAddress(input.nextLine());

        System.out.print("Telephone: +");
        //  user.setTelephone(input.nextInt());

        System.out.print("Email: ");
        //  user.setEmail(input.nextLine());

        System.out.print("Role (Admin | Customer | Landlord): ");
        do {
            reqRole = input.nextLine().toUpperCase(Locale.ROOT);
            if(!Roles.isRole(reqRole)) invalidInput();
        } while(!Roles.isRole(reqRole));

        switch (reqRole) {
            case "ADMIN" -> user = new Administrator(username, password);
            case "LANDLORD" -> user = new Landlord(username, password);
            case "CUSTOMER" -> user = new Customer(username, password);
            default -> user = new User(username, password);
        }

        System.out.println("Are you sure you want to create a new account with the following credentials?");
        System.out.println(frame);
        System.out.println("Username\t" + username);
        System.out.println("Password\t" + password);
        System.out.println(frame);
        System.out.println("Press [1] for \"yes\" and [2] for \"canceling\".");
        if(LoginSystem.getChoice()==1)
            return user;
        return null;
    }

    private static int getChoice() {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        while(!Objects.equals(in, "1") && !Objects.equals(in, "2") && !Objects.equals(in, "3")) {
            invalidInput();
            in = input.nextLine();
        }
        return Integer.parseInt(in);
    }


    public static void consoleLoginScreen() {
        User user;
        while (welcomeMessageConsole() != 3)
            switch (welcomeMessageConsole()) {
                case 1:                                                                                                     //Create a new account
                    user = registerUserConsole();
                    if(user == null) continue;
                    System.out.println("Hello " /*+user.getName()*/);
                    System.out.println("Please, wait until one of our administrators activate your account!");
                    //TODO: Add new user to list of users.
                    break;
                case 2:
                   /* System.out.println("Please, log-in with your account details.");
                    int checks = 0;
                    do {
                        if (checks != 0)
                            System.out.println("Wrong Details. " + (4-checks) + " times remaining. Try again.\n");
                        System.out.print("Username: ");
                        username = input.nextLine();
                        System.out.print("Password: ");
                        checks++;
                    } while ((loggedUser = checkUser(username, password)) == null && checks <= 3);
                    if (checks > 3) {
                        System.out.println("Contact administrator!");
                        return;
                    }*/
                    break;
                default:
                    return;
            }

    }

    public static void graphicalLoginScreen(){}

}

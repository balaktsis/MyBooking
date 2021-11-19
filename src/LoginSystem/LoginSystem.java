package LoginSystem;


import Misc.Storage;
import Users.Administrator;
import Users.Customer;
import Users.Landlord;
import Users.User;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class represents a user Log-in interface that allows existing users to sign-in to their accounts or new users
 * to sign up to MyBooking, creating a new account, pending for approval by an administrator. The LoginSystem class
 * consists of both Console and Graphical UI separate methods.
 * @author Christos Balaktsis
 */

public class LoginSystem {
    private final String frame = "===============================";
    private final boolean graphical;

    public LoginSystem(boolean graphical) {
        this.graphical = graphical;
    }

    public void showLoginScreen() {
        if(graphical) graphicalLoginScreen();
        else consoleLoginScreen();
    }

    private int welcomeMessageConsole() {
        System.out.println(frame);
        System.out.println("Welcome to MyBooking!");
        System.out.println(frame);
        System.out.print("Press [1] to create a new account or [2] to sign in: ");
        return getChoice();
    }

    private void invalidInput() {
        System.out.println("****\nInvalid input! Please, try again.\n****");
    }

    private User signUpUserConsole() {
        Scanner input = new Scanner(System.in);
        String username, password, reqRole;
        User user;
        System.out.println("\nPlease, provide the following information and your account will be activated soon!");
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        System.out.print("Address: ");
        ///  user.setAddress(input.nextLine());
        System.out.print("Telephone: +");
        //  user.setTelephone(input.nextInt());
        System.out.print("Email: ");
        //  user.setEmail(input.nextLine());
        System.out.print("Role (Administrator | Customer | Landlord): ");
        do {
            reqRole = input.nextLine().toUpperCase(Locale.ROOT);
            if(!Roles.isRole(reqRole)) invalidInput();
        } while(!Roles.isRole(reqRole));
        switch (reqRole) {
            case "ADMINISTRATOR" -> user = new Administrator(username, password);
            case "LANDLORD" -> user = new Landlord(username, password);
            case "CUSTOMER" -> user = new Customer(username, password);
            default -> user = new User(username, password);
        }
        System.out.println("Are you sure you want to create a new account with the following credentials?");
        System.out.println(frame);
        System.out.println("Username\t" + username);
        System.out.println("Password\t" + password);
        System.out.println(frame);
        System.out.println("Press [1] for \"yes\" or [2] for \"canceling\".");
        if(getChoice()==1)
            return user;
        return null;
    }

    private User checkUser(String name, String pass) {
        User tempUser = new User(name, pass);
        return Storage.regUsers.contains(tempUser) ? tempUser : null;
    }

    private User signInUserConsole() {
        Scanner input = new Scanner(System.in);
        String username, password, reqRole;
        User user;
        int checks = 0;
        System.out.println("\nPlease, log-in with your personal account credentials.");
        do {
            if (checks != 0) System.out.println("Wrong Details. " + (4-checks) + " times remaining. Try again.\n");
            System.out.print("Username: ");
            username = input.nextLine();
            System.out.print("Password: ");
            password = input.nextLine();
            checks++;
        } while ((user = checkUser(username, password)) == null && checks <= 3);
        if (checks > 3) System.out.println("Access denied. Contact administrator!");
        return user;
    }

    private int getChoice() {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        while(!Objects.equals(in, "1") && !Objects.equals(in, "2") && !Objects.equals(in, "3")) {
            invalidInput();
            in = input.nextLine();
        }
        return Integer.parseInt(in);
    }

    private void consoleLoginScreen() {
        User user;
        int choice;
        while ((choice=welcomeMessageConsole())!= 3)
            switch (choice) {
                case 1 -> {                                                                                             //Create a new account.
                    user = signUpUserConsole();
                    if (user == null) continue;
                    System.out.println("Hello " /*+user.getName()*/);
                    System.out.println("Please, wait until one of our administrators activate your account!");
                    //TODO: Add new user to list of users.
                }
                case 2 -> {                                                                                             //Log-in to an existing account.
                    user = signInUserConsole();
                    if (user == null) return;
                    System.out.println("Hello " /*+user.getName()*/);
                    user.showInterface(false);
                }
            }
    }

    private void graphicalLoginScreen(){}

}

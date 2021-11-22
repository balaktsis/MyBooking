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
 *
 * @author Christos Balaktsis
 */

public class LoginSystem {
    private final String frame = "===============================";
    private final boolean graphical;
    private final Storage storage;

    /**
     * Default constructor of class.
     * @param graphical Specifies if the MyBooking system operates in GUI or CommandLine (console).
     * @param storage   The storage system of the app.
     */
    public LoginSystem(boolean graphical, Storage storage) {
        this.graphical = graphical;
        this.storage = storage;
    }

    /**
     * Method that calls GUI or CommandLine (console) log-in interface, according to graphical boolean field.
     */
    public void showLoginScreen() {
        if (this.graphical) graphicalLoginScreen();
        else consoleLoginScreen();
    }

    /**
     * The Welcome CommandLine Screen, when starting the application.
     * @return integer that represents an action.
     */
    private int welcomeMessageConsole() {
        System.out.println(frame);
        System.out.println("Welcome to MyBooking!");
        System.out.println(frame);
        System.out.print("Press\n[1] to create a new account\n[2] to sign in\n[3] to exit\nChoice: ");
        return consoleGetChoice();
    }

    private void invalidInput() {
        System.out.println("****\nInvalid input! Please, try again.\n****");
    }

    /**
     * Signing-un method for a new user.
     * @return the user object or null, if the credentials do not match to any registered user.
     */
    private User signUpUserConsole() {
        Scanner input = new Scanner(System.in);
        String username, password, reqRole;
        User user;
        System.out.println("\nPlease, provide the following information and your account will be activated soon!");
        do {
            System.out.print("Username: ");
            username = input.nextLine();
            System.out.print("Password: ");
            password = input.nextLine();
            if (checkUsername(username)) System.out.println("Username already taken. Please choose another one.");
            if (checkPassword(password))
                System.out.println("Password should contain at least one digit and has length between 6 and 20 characters. Please choose another one.");
        } while (checkUsername(username) || checkPassword(password));
      /*  System.out.print("Address: ");
        user.setAddress(input.nextLine());
        System.out.print("Telephone: +");
        user.setTelephone(input.nextInt());
        System.out.print("Email: ");
        user.setEmail(input.nextLine()); */
        do {
            System.out.print("Role (Administrator | Customer | Landlord): ");
            reqRole = input.nextLine().toUpperCase(Locale.ROOT);
            if (!Roles.isRole(reqRole)) invalidInput();
        } while (!Roles.isRole(reqRole));
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
        System.out.println("Press [1] for yes or [2] to cancel.");
        if (consoleGetChoice() == 1)
            return user;
        return null;
    }

    /**
     * @param name Provided username for checking.
     * @return if the username is in use.
     */
    private boolean checkUsername(String name) {
        for(User user : this.storage.getRegUsers())
            if(Objects.equals(user.getUsername(), name)) return true;
        return false;
    }

    /**
     * @param pass Provided password for formula checking.
     * @return if the password meets specific conditions: must contain at least one digit and has length between 6 and 20 characters.
     */
    private boolean checkPassword(String pass) {
        return !pass.matches(".*[0-9]+.*") || pass.length() < 6 || pass.length() > 20;
    }

    /**
     * @param name Provided username.
     * @param pass Provided password.
     * @return if user with username=name and password=pass is already registered.
     */
    private User checkUser(String name, String pass) {
        User tempUser = new User(name, pass);
        for(User user : this.storage.getRegUsers())
            if(user.equals(tempUser)) return user;
        return null;
    }

    /**
     * Signing-in method for an existing user.
     * @return the user object or null, if the credentials do not match to any registered user.
     */
    private User signInUserConsole() {
        Scanner input = new Scanner(System.in);
        String username, password, reqRole;
        User user;
        int checks = 0;
        System.out.println("\nPlease, log-in with your personal account credentials.");
        do {
            if (checks != 0) System.out.println("Wrong Details. " + (4 - checks) + " times remaining. Try again.\n");
            System.out.print("Username: ");
            username = input.nextLine();
            System.out.print("Password: ");
            password = input.nextLine();
            checks++;
        } while ((user = checkUser(username, password)) == null && checks <= 3);
        if (checks > 3) System.out.println("Access denied. Contact administrator!");
        return user;
    }

    /**
     * Gets the console input for a specific operation.
     * @return integer between 1 and 3
     */
    private int consoleGetChoice() {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        while (!Objects.equals(in, "1") && !Objects.equals(in, "2") && !Objects.equals(in, "3")) {
            invalidInput();
            in = input.nextLine();
        }
        return Integer.parseInt(in);
    }

    /**
     * The log-in screen CLI. The user chooses either to sign-in or sign-up and inputs the appropriate credentials/info.
     */
    private void consoleLoginScreen() {
        User user;
        int choice;
        while ((choice = welcomeMessageConsole()) != 3) {
            switch (choice) {
                case 1 -> {                                                                                             //Create a new account.
                    user = signUpUserConsole();
                    if (user == null) continue;
                    System.out.println("Hello " + user.getUsername() + ".");
                    System.out.println("Please, wait until one of our administrators activate your account!\n");
                    storage.getRegUsers().add(user);
                }
                case 2 -> {                                                                                             //Log-in to an existing account.
                    user = signInUserConsole();
                    if (user == null) return;
                    System.out.println("Hello " + user.getUsername() + ".");
                    user.showInterface(false);
                }
            }
        }
        System.out.println("Thank you for using MyBooking! See you!");
    }

    private void graphicalLoginScreen() {
    }

}
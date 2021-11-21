import LoginSystem.LoginSystem;
import Misc.Storage;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        AppSystem appSystem = new AppSystem(storage);
        LoginSystem loginSystem = new LoginSystem(false, storage);
        loginSystem.showLoginScreen();
    }
}

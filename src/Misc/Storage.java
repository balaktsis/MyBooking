package Misc;

import Users.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    //private ArrayList<> runtimeArray = null;
    //TODO I have no idea what I'm doing :/

    private HashMap<String, User> regUsers;

    public Storage() {
        regUsers = new HashMap<>();
    }

    public HashMap<String, User> getRegUsers() {
        return regUsers;
    }

}

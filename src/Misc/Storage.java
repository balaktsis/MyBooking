package Misc;

import Users.*;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.HashSet;

public class Storage {
    //private ArrayList<> runtimeArray = null;
    //TODO I have no idea what I'm doing :/

    private HashSet<User> regUsers;

    public Storage() {
        regUsers = new HashSet<>();
    }

    public HashSet<User> getRegUsers() {
        return regUsers;
    }

}

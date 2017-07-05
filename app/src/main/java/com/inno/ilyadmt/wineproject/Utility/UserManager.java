package com.inno.ilyadmt.wineproject.Utility;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mjazz on 25.06.2017.
 */

public class UserManager {
    public HashMap<User, Pair<String, ROLES>> internalStorage = new HashMap<>();

    public List<User> getUsersList(){
        return new ArrayList<>(internalStorage.keySet());
    }

    private static final UserManager ourInstance = new UserManager(){{
        internalStorage.put(new User("admin", "admin", "admin", ROLES.ADMIN), new Pair<String, ROLES>("1", ROLES.ADMIN));
    }};

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {

    }
}

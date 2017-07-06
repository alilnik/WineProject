package com.inno.ilyadmt.wineproject.Utility;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mjazz on 25.06.2017.
 */

public class UserManager {
    public HashMap<String, Pair<String, User>> internalStorage = new HashMap<>();

    public List<Pair<String, User>> getUsersList(){
        return new ArrayList<>(internalStorage.values());
    }

    private static final UserManager ourInstance = new UserManager(){{
        internalStorage.put("admin", new Pair<String, User>("1", new User("admin", "admin", "admin", ROLES.ADMIN)));
    }};

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {

    }
}

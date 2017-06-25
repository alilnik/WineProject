package com.inno.ilyadmt.wineproject;

import java.util.HashMap;

/**
 * Created by mjazz on 25.06.2017.
 */

public class Storage {
    public HashMap<String, String> internalStorage = new HashMap<>();

    private static final Storage ourInstance = new Storage();

    public static Storage getInstance() {
        return ourInstance;
    }

    private Storage() {
    }
}

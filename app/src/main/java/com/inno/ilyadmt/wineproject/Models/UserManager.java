package com.inno.ilyadmt.wineproject.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts;
import com.inno.ilyadmt.wineproject.DatabaseManager.DBManager;
import com.inno.ilyadmt.wineproject.Utility.Data;
import com.inno.ilyadmt.wineproject.Utility.ROLES;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.*;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_PASSWORD;

/**
 * Created by mjazz on 25.06.2017.
 */

public class UserManager {

    private static DBManager dbManager;

    private UserManager() {}

    private static class UserManagerHolder{
        private static final UserManager instance = new UserManager();
    }

    public static UserManager getInstance(Context context) {
        if(dbManager == null) dbManager = DBManager.getInstance(context);
        return UserManagerHolder.instance;
    }

    public SQLiteDatabase getWritableDB(){
        return dbManager.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDB(){
        return dbManager.getReadableDatabase();
    }

    public HashMap<String, Pair<String, User>> internalStorage = new HashMap<>();

    public List<Pair<String, User>> getUsersList(){

        List<Pair<String, User>> users = new ArrayList<>();
        SQLiteDatabase db = dbManager.getWritableDatabase();

        if(db == null){
            Log.d("USER_MANAGER", "Error recieving writable db in getUserList");
            Toast.makeText(dbManager.getContext(), "Sorry, there was a problem with loading the list!\nPlease, try again", Toast.LENGTH_LONG).show();
            return users;
        }

        Cursor cursor = db.rawQuery("select * from " + DBContracts.User.TABLE_NAME_USERS + ";", null);
        String username, name, surname, password;
        ROLES role;

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                username = cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_USERNAME));
                name = cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_NAME));
                surname = cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_SURNAME));
                password = cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_PASSWORD));
                role = ROLES.valueOf(cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_ROLE)).toUpperCase());
                users.add(new Pair<String, User>(password, new User(name, surname, username, role)));
                cursor.moveToNext();
            }
        }
        return users;
    }


    public ROLES checkPassword(String username, String password){

        SQLiteDatabase db = dbManager.getReadableDatabase();

        if(db == null){
            Log.d("USER_MANAGER", "Error recieving readable db in checkPassword");
            Toast.makeText(dbManager.getContext(), "Sorry, there was a problem with logging in.\nPlease, try again.", Toast.LENGTH_LONG).show();
            return null;
        }

        Cursor cursor = db.query(
                DBContracts.User.TABLE_NAME_USERS,                           // The table to query
                new String[]{COLUMN_ENTRY_PASSWORD, COLUMN_ENTRY_ROLE} ,                      // The columns to return
                COLUMN_ENTRY_USERNAME+"=?",                                     // The columns for the WHERE clause
                new String[]{username},                                    // The values for the WHERE clause
                null,                                                      // don't group the rows
                null,                                                      // don't filter by row groups
                null
        );
        if(cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        if(cursor.getCount() >= 1)
            while (cursor.moveToNext())
                if (cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_PASSWORD)).equals(password)) {
                    ROLES role = ROLES.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY_ROLE)));
                    cursor.close();
                    return role;
                }

        cursor.close();
        return null;
    }

    public void addUser(String username, String name, String surname, String password, ROLES role){

        SQLiteDatabase db = dbManager.getReadableDatabase();

        if(db == null){
            Log.d("USER_MANAGER", "Error recieving readable db in addUser");
            Toast.makeText(dbManager.getContext(), "Sorry, there was a problem adding user.\nPlease, try again.", Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENTRY_ID, DBManager.getID());
        dbManager.udpateID();
        contentValues.put(COLUMN_ENTRY_NAME, name);
        contentValues.put(COLUMN_ENTRY_SURNAME, surname);
        contentValues.put(COLUMN_ENTRY_ROLE, role.toString().toUpperCase());
        contentValues.put(COLUMN_ENTRY_USERNAME, username);
        contentValues.put(COLUMN_ENTRY_PASSWORD, password);
        db.insert(TABLE_NAME_USERS, null, contentValues);
    }

    public int addUser(ContentValues contentValues){

        SQLiteDatabase db = dbManager.getReadableDatabase();

        if(db == null){
            Log.d("USER_MANAGER", "Error recieving readable db in addUser");
            Toast.makeText(dbManager.getContext(), "Sorry, there was a problem adding user.\nPlease, try again.", Toast.LENGTH_LONG).show();
            return -1;
        }

        int id = DBManager.getID();
        contentValues.put(COLUMN_ENTRY_ID, id);
        dbManager.udpateID();

        db.insert(TABLE_NAME_USERS, null, contentValues);
        return id;
    }

    public boolean containsUser(String username){
        SQLiteDatabase db = dbManager.getReadableDatabase();

        if(db == null){
            Log.d("USER_MANAGER", "Error recieving readable db in containsUser");
            Toast.makeText(dbManager.getContext(), "Sorry, there was a problem with accessing database.\nPlease, try again.", Toast.LENGTH_LONG).show();
            return false;
        }
        Cursor cursor = db.query(
                DBContracts.User.TABLE_NAME_USERS,                           // The table to query
                new String[]{COLUMN_ENTRY_USERNAME} ,                      // The columns to return
                COLUMN_ENTRY_USERNAME,                                     // The columns for the WHERE clause
                new String[]{username},                                    // The values for the WHERE clause
                null,                                                      // don't group the rows
                null,                                                      // don't filter by row groups
                null
        );
        return cursor.getCount() != 0;
    }

    public static User createDummyUser(SecureRandom secureRandom){
        Random random = new Random();
        int name = random.nextInt(Data.NAMES.length);
        int surname = random.nextInt(Data.SURNAMES.length);
        String username = new BigInteger(130, secureRandom).toString(32);
        return new User(Data.NAMES[name], Data.SURNAMES[surname], username, ROLES.USER);
    }




}

package com.inno.ilyadmt.wineproject.DatabaseManager;

/**
 * Created by mjazz on 07.07.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.Models.User;
import com.inno.ilyadmt.wineproject.Models.UserManager;

import java.math.BigInteger;
import java.security.SecureRandom;

import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_ID;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_NAME;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_PASSWORD;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_ROLE;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_SURNAME;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.COLUMN_ENTRY_USERNAME;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.SQL_CREATE_USER_TABLE;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.SQL_DELETE_ENTRIES;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.TABLE_NAME_USERS;


public class DBManager extends SQLiteOpenHelper {

    static Context context;
    private static int ID;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "users.db";

    public void udpateID() {
        ID++;
    }


    public static int getID() {
        return ID;
    }


    private static class DBManagerHolder {
        private static DBManager dbManagerInstance;
    }


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DBManager.context = context;
    }

    public static DBManager getInstance(Context context) {
        if (DBManagerHolder.dbManagerInstance == null) {
            DBManagerHolder.dbManagerInstance = new DBManager(context.getApplicationContext());
        }
        return DBManagerHolder.dbManagerInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            Log.d("DB_LOG", "DB creation: the database is null.");
            Toast.makeText(context, "Sorry, there was a problem with loading your data!\nPlease, try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        db.execSQL(SQL_CREATE_USER_TABLE);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENTRY_ID, ID++);
        contentValues.put(COLUMN_ENTRY_NAME, "admin");
        contentValues.put(COLUMN_ENTRY_SURNAME, "admin");
        contentValues.put(COLUMN_ENTRY_ROLE, "ADMIN");
        contentValues.put(COLUMN_ENTRY_USERNAME, "admin");
        contentValues.put(COLUMN_ENTRY_PASSWORD, "admin");
        db.insert(TABLE_NAME_USERS, null, contentValues);
        populateDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }


    public Context getContext() {
        return context;
    }

    public void populateDatabase(SQLiteDatabase db) {
        if (db == null) {
            Log.d("DB_LOG", "The database is null");
            return;
        }
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 0; i++) {
            ContentValues contentValues = new ContentValues();
            User user = UserManager.createDummyUser(secureRandom);
            contentValues.put(COLUMN_ENTRY_ID, ID++);
            contentValues.put(COLUMN_ENTRY_NAME, user.getName());
            contentValues.put(COLUMN_ENTRY_SURNAME, user.getSurname());
            contentValues.put(COLUMN_ENTRY_ROLE, String.valueOf(user.getRole()));
            contentValues.put(COLUMN_ENTRY_USERNAME, user.getUsername());
            contentValues.put(COLUMN_ENTRY_PASSWORD, new BigInteger(130, secureRandom).toString(32));
            db.insert(TABLE_NAME_USERS, null, contentValues);
        }

    }

}
package com.inno.ilyadmt.wineproject.DatabaseManager;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by mjazz on 12.07.2017.
 */

public final class DBContracts {

    public final static String CONTENT_AUTHORITY = "com.inno.ilyadmt.wineproject.provider";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String RELATIVE_TODO_URI = "users";

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";

    public static abstract class User implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath
                (RELATIVE_TODO_URI).build();

        // Entire table
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.showcase" + "" +
                ".wineproject.provider.users";
        // Single row within the table
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.showcase"
                + ".wineproject.provider.users";

        // Table name
        public static final String TABLE_NAME_USERS = "users";

        public static final String COLUMN_ENTRY_ID = "_id";
        public static final String COLUMN_ENTRY_PASSWORD = "password";
        public static final String COLUMN_ENTRY_USERNAME = "username";
        public static final String COLUMN_ENTRY_NAME = "name";
        public static final String COLUMN_ENTRY_SURNAME = "surname";
        public static final String COLUMN_ENTRY_ROLE = "role";


        public static final String SQL_CREATE_USER_TABLE =
                "CREATE TABLE " + TABLE_NAME_USERS + "( " +
                        COLUMN_ENTRY_ID +" INTEGER PRIMARY KEY," +
                        COLUMN_ENTRY_PASSWORD + TEXT_TYPE + COMMA_SEP+
                        COLUMN_ENTRY_USERNAME + TEXT_TYPE + COMMA_SEP+
                        COLUMN_ENTRY_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ENTRY_SURNAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_ENTRY_ROLE + TEXT_TYPE  + ");";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME_USERS;

        public static Uri buildRowUri(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}

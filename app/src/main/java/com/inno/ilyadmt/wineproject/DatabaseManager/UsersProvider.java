package com.inno.ilyadmt.wineproject.DatabaseManager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.CONTENT_ITEM_TYPE;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.CONTENT_TYPE;
import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.TABLE_NAME_USERS;

/**
 * Created by mjazz on 16.07.2017.
 */

public class UsersProvider extends ContentProvider {

    private static final int USERS_TABLE_ID = 100;
    private static final int USERS_ROW_ID = 101;
    private DBManager mDbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        sUriMatcher.addURI(DBContracts.CONTENT_AUTHORITY, DBContracts.RELATIVE_TODO_URI,
                USERS_TABLE_ID);
        sUriMatcher.addURI(DBContracts.CONTENT_AUTHORITY, DBContracts.RELATIVE_TODO_URI + "/#",
                USERS_ROW_ID);
    }


    @Override
    public boolean onCreate() {
        mDbHelper = DBManager.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[]
            selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor retCursor;
        switch (sUriMatcher.match(uri)){
            case USERS_TABLE_ID:
                retCursor = db.query(DBContracts.User.TABLE_NAME_USERS, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;

            case USERS_ROW_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(DBContracts.User.TABLE_NAME_USERS, projection, DBContracts.User.COLUMN_ENTRY_ID + " = ?", new String[]{String.valueOf(_id)}, null, null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri))
        {
            case USERS_TABLE_ID:
                return CONTENT_TYPE;

            case USERS_ROW_ID:
                return CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        if (sUriMatcher.match(uri) == USERS_TABLE_ID)
        {
            _id = db.insert(TABLE_NAME_USERS, null, contentValues);
            if (_id > 0)
            {
                returnUri = DBContracts.User.buildRowUri(_id);
            }
            else
            {
                throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
            }
        }
        else
        {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[]
            selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rows = 0;
        if (sUriMatcher.match(uri) == USERS_TABLE_ID)
        {
            rows = db.update(TABLE_NAME_USERS, values, selection, selectionArgs);
        }
        else
        {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rows != 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }
}

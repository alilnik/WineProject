package com.inno.ilyadmt.wineproject.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts;
import com.inno.ilyadmt.wineproject.DatabaseManager.QueryHandler;
import com.inno.ilyadmt.wineproject.Models.UserManager;
import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.ROLES;

import static com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts.User.CONTENT_URI;

/**
 * Created by mjazz on 13.07.2017.
 */

public class AdminAddUserActivity extends Activity {

    UserManager userManager = UserManager.getInstance(this);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void add_cancel(View view) {
        finish();
    }

    public void add_add(View view) {
        QueryHandler queryHandler = new QueryHandler(this, null);
        String username = ((EditText) findViewById(R.id.edt_add_login)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.edt_add_pwd)).getText().toString();
        String name  = ((EditText)findViewById(R.id.edt_add_name)).getText().toString();
        String surname  = ((EditText)findViewById(R.id.edt_add_surname)).getText().toString();
        ROLES role = ROLES.valueOf(((Spinner)findViewById(R.id.spinner_add_role)).getSelectedItem().toString().toUpperCase());
        ContentValues values = new ContentValues();
        values.put(DBContracts.User.COLUMN_ENTRY_USERNAME, username);
        values.put(DBContracts.User.COLUMN_ENTRY_NAME, name);
        values.put(DBContracts.User.COLUMN_ENTRY_SURNAME, surname);
        values.put(DBContracts.User.COLUMN_ENTRY_ROLE, role.toString());


        queryHandler.startInsert(QueryHandler.OperationToken.TOKEN_INSERT, null, CONTENT_URI, values);
        finish();
//        userManager.addUser(username, name, surname, pwd, role);
//        finish();
    }
}

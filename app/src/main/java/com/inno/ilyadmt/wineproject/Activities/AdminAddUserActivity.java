package com.inno.ilyadmt.wineproject.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.inno.ilyadmt.wineproject.Models.UserManager;
import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.ROLES;

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
        String username = ((EditText) findViewById(R.id.edt_add_login)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.edt_add_pwd)).getText().toString();
        String name  = ((EditText)findViewById(R.id.edt_add_name)).getText().toString();
        String surname  = ((EditText)findViewById(R.id.edt_add_surname)).getText().toString();
        ROLES role = ROLES.valueOf(((Spinner)findViewById(R.id.spinner_add_role)).getSelectedItem().toString().toUpperCase());
        userManager.addUser(username, name, surname, pwd, role);
        finish();
    }
}

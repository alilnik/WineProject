package com.inno.ilyadmt.wineproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.ROLES;
import com.inno.ilyadmt.wineproject.Utility.User;
import com.inno.ilyadmt.wineproject.Utility.UserManager;

import static com.inno.ilyadmt.wineproject.Utility.ROLES.USER;

/**
 * Created by mjazz on 25.06.2017.
 */
public class RegisterActivity extends Activity {

    UserManager storage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        storage = UserManager.getInstance();
    }

    public void register_cancel(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void register_register(View view) {
        String login = ((EditText) findViewById(R.id.edt_reg_login)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.edt_reg_pwd)).getText().toString();
        String pwd_rep = ((EditText) findViewById(R.id.edt_reg_pwd_rep)).getText().toString();
        String name  = ((EditText)findViewById(R.id.edt_reg_name)).getText().toString();
        String surname  = ((EditText)findViewById(R.id.edt_reg_surname)).getText().toString();

        ROLES role = ROLES.valueOf(((Spinner)findViewById(R.id.spinner_reg_role)).getSelectedItem().toString().toUpperCase());

        if(pwd.length()<8){
            Toast.makeText(this, "Password should be more than 8 characters long.\nCheck it, please.", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!pwd.equals(pwd_rep)){
            Toast.makeText(this, "Passwords do not match.\nCheck, please.", Toast.LENGTH_LONG).show();
            return;
        }
        else if(storage.internalStorage.containsKey(login)){
            Toast.makeText(this, "This login is occupied already.\nConsider other, please.", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            storage.internalStorage.put(new User(name, surname, login, role), new Pair<String, ROLES>(pwd, role));
            Toast.makeText(this, "You have registered successfully!\nPlease, log in now.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}


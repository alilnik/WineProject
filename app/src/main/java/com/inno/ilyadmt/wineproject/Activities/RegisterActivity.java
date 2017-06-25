package com.inno.ilyadmt.wineproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Storage;

/**
 * Created by mjazz on 25.06.2017.
 */
public class RegisterActivity extends Activity {

    Storage storage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        storage = Storage.getInstance();
    }

    public void register_cancel(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void register_register(View view) {
        String login = ((EditText) findViewById(R.id.edt_reg_login)).getText().toString();
        String pwd = ((EditText) findViewById(R.id.edt_reg_pwd)).getText().toString();
        String pwd_rep = ((EditText) findViewById(R.id.edt_reg_pwd_rep)).getText().toString();

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
            storage.internalStorage.put(login, pwd);
            Toast.makeText(this, "You have registered successfully!\nPlease, log in now.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}


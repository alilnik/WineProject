package com.inno.ilyadmt.wineproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Storage;

public class LoginActivity extends AppCompatActivity {


    private String login;
    private String pwd;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storage = Storage.getInstance();
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void login(View view) {
        login = ((EditText)findViewById(R.id.edt_login_login)).getText().toString();
        pwd = ((EditText)findViewById(R.id.edt_login_pwd)).getText().toString();

        if(checkPassword(login, pwd)) startActivity(new Intent(this, HomeScreenActivity.class));
        else Toast.makeText(this, "Login or password is incorrect.\nCheck them, please.", Toast.LENGTH_LONG).show();
    }

    private boolean checkPassword(String user, String password){
        String localPassword = storage.internalStorage.get(user);
        if(localPassword != null){
            if(localPassword.equals(password)) return true;
            return false;
        }
        return false;

    }
}

package com.inno.ilyadmt.wineproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.ROLES;
import com.inno.ilyadmt.wineproject.Models.UserManager;

public class LoginActivity extends AppCompatActivity {


    private String login;
    private String pwd;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userManager = UserManager.getInstance(this);
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, QRActivity.class));
    }

    public void login(View view) {
        login = ((EditText)findViewById(R.id.edt_login_login)).getText().toString();
        pwd = ((EditText)findViewById(R.id.edt_login_pwd)).getText().toString();
        ROLES role;

        if((role = userManager.checkPassword(login, pwd))!=null){
            if(role.equals(ROLES.USER)){
                Intent intent = new Intent(this, HomeScreenActivity.class);
                intent.putExtra("name", "John");
                intent.putExtra("surname", "Doe");
                intent.putExtra("secname", "Doe");

                startActivity(intent);
            }
            else startActivity(new Intent(this, AdminActivity.class));

        }
        else Toast.makeText(this, "Login or password is incorrect.\nCheck them, please.", Toast.LENGTH_LONG).show();
    }

}

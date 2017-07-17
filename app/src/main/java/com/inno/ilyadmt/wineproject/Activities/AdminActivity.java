package com.inno.ilyadmt.wineproject.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.inno.ilyadmt.wineproject.Fragments.UserListFragment;

import com.inno.ilyadmt.wineproject.R;

/**
 * Created by mjazz on 04.07.2017.
 */

public class AdminActivity extends AppCompatActivity implements UserListFragment.UsersListFragmentListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        showTodoListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    private void showTodoListFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.constraint_layout_admin, new UserListFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void displayUsersDetailsFragment(Bundle bundle) {

    }

    public void addUser(MenuItem item) {
        startActivity(new Intent(this, AdminAddUserActivity.class));
    }

    public void scanQR(MenuItem item) {
    }
}

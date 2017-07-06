package com.inno.ilyadmt.wineproject.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import com.inno.ilyadmt.wineproject.Adapters.UsersAdapter;
import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.User;
import com.inno.ilyadmt.wineproject.Utility.UserManager;

import java.util.List;

/**
 * Created by mjazz on 04.07.2017.
 */

public class AdminActivity extends Activity {

    List<Pair<String, User>> userList = UserManager.getInstance().getUsersList();
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_administrator);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_user_list);
        usersAdapter = new UsersAdapter(this, userList);
        RecyclerView.LayoutManager usersLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(usersLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(usersAdapter);
    }
}

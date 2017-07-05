package com.inno.ilyadmt.wineproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inno.ilyadmt.wineproject.Activities.HomeScreenActivity;
import com.inno.ilyadmt.wineproject.R;
import com.inno.ilyadmt.wineproject.Utility.User;

import java.util.List;

/**
 * Created by mjazz on 05.07.2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    Context context;
    List<User> users;

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, username, role;
        List<User> users;
        Context context;

        public MyViewHolder(View itemView, List<User> users, Context context) {
            super(itemView);
            this.users = users;
            this.context = context;
            itemView.setOnClickListener(this);
            name = (TextView)itemView.findViewById(R.id.adp_userlist_tv_name);
            username = (TextView)itemView.findViewById(R.id.adp_userlist_tv_username);
            role = (TextView) itemView.findViewById(R.id.adp_userlist_tv_role);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            User user= this.users.get(position);
            Intent intent = new Intent(context, HomeScreenActivity.class);
            this.context.startActivity(intent);
        }
    }

    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user_list_row, parent, false);
        return new UsersAdapter.MyViewHolder(itemView, users, context);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);

        holder.name.setText(user.getName() + " " +user.getSurname());
        holder.username.setText(user.getUsername());
        holder.role.setText(user.getRole().toString());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

package com.inno.ilyadmt.wineproject.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts;
import com.inno.ilyadmt.wineproject.R;

/**
 * Created by mjazz on 16.07.2017.
 */

public class UsersListAdapter extends CursorRecyclerAdapter<UsersListAdapter.ItemViewHolder>{

    private OnRowClickListener mClickListener;

    public interface OnRowClickListener
    {
        void onRowClick(Uri uri);

        void onRowLongClick(String message, int row_id);
    }

    public UsersListAdapter(Context context, Cursor cursor, OnRowClickListener clickListener) {
        super(cursor);
        Resources resources = context.getResources();
        mClickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, Cursor cursor) {
        holder.name.setText(cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_NAME)));
        holder.username.setText(cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_USERNAME)));
        holder.role.setText(cursor.getString(cursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_ROLE)));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user_list_row, parent, false));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView name, username, role;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adp_userlist_tv_name);
            username = itemView.findViewById(R.id.adp_userlist_tv_username);
            role = itemView.findViewById(R.id.adp_userlist_tv_role);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
            {
                if (mCursor.moveToPosition(getAdapterPosition()))
                {
                    int columnIdIndex = mCursor.getColumnIndex(DBContracts.User._ID);
                    Uri uri = DBContracts.User.buildRowUri(mCursor.getInt(columnIdIndex));
                    mClickListener.onRowClick(uri);
                }
            }
        }

        @Override
        public boolean onLongClick(View view)
        {
            if (mClickListener != null)
            {
                if (mCursor.moveToPosition(getAdapterPosition()))
                {
                    int columnIdIndex = mCursor.getColumnIndex(DBContracts.User._ID);
                    int columnDataIndex = mCursor.getColumnIndex(DBContracts.User.COLUMN_ENTRY_USERNAME);
                    mClickListener.onRowLongClick(mCursor.getString(columnDataIndex), mCursor
                            .getInt(columnIdIndex));
                }
            }
            return true;
        }
    }
}

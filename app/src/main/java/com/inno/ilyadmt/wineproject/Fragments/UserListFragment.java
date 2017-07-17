package com.inno.ilyadmt.wineproject.Fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.inno.ilyadmt.wineproject.Adapters.UsersListAdapter;
import com.inno.ilyadmt.wineproject.DatabaseManager.DBContracts;
import com.inno.ilyadmt.wineproject.R;

/**
 * Created by mjazz on 05.07.2017.
 */

public class UserListFragment extends android.support.v4.app.Fragment implements UsersListAdapter.OnRowClickListener, android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private UsersListAdapter adapter;
    private UsersListFragmentListener fragmentListener;
    private ViewSwitcher switcher;
    public static final String URI_KEY = "uri";
    private static final int LOADER_ID = 1;

    public UserListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof UsersListFragmentListener)
        {
            fragmentListener = (UsersListFragmentListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        getLoaderManager().initLoader(LOADER_ID, null, this);
        adapter = new UsersListAdapter(getContext(), null, this);
        switcher = (ViewSwitcher) view.findViewById(R.id.fragment_users_list_switcher);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_user_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onRowClick(Uri uri) {

    }

    @Override
    public void onRowLongClick(String message, int row_id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {DBContracts.User._ID, DBContracts.User.COLUMN_ENTRY_USERNAME,
                DBContracts.User.COLUMN_ENTRY_ROLE, DBContracts.User.COLUMN_ENTRY_NAME};
        return new CursorLoader(getContext(), DBContracts.User.CONTENT_URI, projection, null,
                null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        if (data.getCount() > 0)
        {
            adapter.swapCursor(data);

            if (R.id.recycler_user_list == switcher.getNextView().getId())
            {
                switcher.showNext();
            }
        }
        else if (R.id.fragment_users_list_tv_empty_text == switcher.getNextView().getId())
        {
            switcher.showNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public interface UsersListFragmentListener
    {
        void displayUsersDetailsFragment(Bundle bundle);
    }
}

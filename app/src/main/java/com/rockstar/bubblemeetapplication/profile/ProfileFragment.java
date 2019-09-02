package com.rockstar.bubblemeetapplication.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.DataAdapter;
import com.rockstar.bubblemeetapplication.R;

public class ProfileFragment extends Fragment implements BaseContract.BaseView {

    String mName;

    TextView mTextViewName;
    RecyclerView mRecyclerViewUsers;

    public void setName(String name){
        mName = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_user_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerViewUsers = (RecyclerView) view.findViewById(com.rockstar.bubblemeetapplication.R.id.recyclerViewUsers);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewUsers.setLayoutManager(layoutManager);
        mRecyclerViewUsers.setAdapter(new ProfileCustomAdapter());
        mTextViewName = (TextView) view.findViewById(R.id.textViewName);
        mTextViewName.setText(mName);
        initViews();
    }

    @Override
    public void initViews() {

    }
}

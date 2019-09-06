package com.rockstar.bubblemeetapplication.Inbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.DataAdapter;
import com.rockstar.bubblemeetapplication.main.MainActivity;

public class InboxFragment extends Fragment implements BaseContract.BaseView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_inbox, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews();
    }

    @Override
    public void initViews() {
        ((MainActivity) getActivity()).hideButtonBack();
    }
}
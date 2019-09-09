package com.rockstar.bubblemeetapplication.matches;

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
import com.rockstar.bubblemeetapplication.model.data.UserData;

import java.util.ArrayList;

public class MatchesFragment extends Fragment implements BaseContract.BaseView {

    private DataAdapter mAdapter;
    private MatchesPresenter mPresenter;
    private GridView mGridViewMatches;

    public MatchesFragment(){
        mPresenter = new MatchesPresenter(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_grid_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridViewMatches = (GridView) view.findViewById(com.rockstar.bubblemeetapplication.R.id.gridViewList);
        mAdapter = new DataAdapter((MainActivity) getActivity(), getFragmentManager(), new ArrayList<UserData>());
        mGridViewMatches.setAdapter(mAdapter);
        initViews();
    }

    @Override
    public void initViews() {
        ((MainActivity) getActivity()).hideButtonBack();
    }

    public void addUsers(ArrayList<UserData> newUsers){
        mAdapter.addUsers(newUsers);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}
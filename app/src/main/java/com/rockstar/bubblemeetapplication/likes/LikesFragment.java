package com.rockstar.bubblemeetapplication.likes;

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

public class LikesFragment extends Fragment implements BaseContract.BaseView {

    private LikesPresenter mPresenter;
    private GridView mGridViewMatches;

    @Override
    public void onStart(){
        super.onStart();
        mPresenter = new LikesPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_grid_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mGridViewMatches = (GridView) view.findViewById(com.rockstar.bubblemeetapplication.R.id.gridViewList);
        mGridViewMatches.setAdapter(new DataAdapter(getContext(), new String[]{"Fragment", "Likes", "Names", "Hello", "Vasya", "Dog"}, getFragmentManager(), (MainActivity) getActivity()));
        initViews();
        mPresenter.onStart();
    }

    @Override
    public void initViews() {
        ((MainActivity) getActivity()).hideButtonBack();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPresenter.onStop();
    }
}
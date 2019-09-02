package com.rockstar.bubblemeetapplication.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
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
    ImageView mImageViewLike;
    ImageView mImageViewBoom;
    ImageView mImageViewAvatar;
    ImageView mImageViewBoomWhiteCircle;
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
        mTextViewName = (TextView) view.findViewById(R.id.textViewName);
        mImageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);
        mImageViewBoom = (ImageView) view.findViewById(R.id.imageViewBoom);
        mImageViewAvatar = (ImageView) view.findViewById(R.id.imageViewAvatar);
        mImageViewBoomWhiteCircle = (ImageView) view.findViewById(R.id.imageViewBoomWhiteCircle);
        initViews();
    }

    @Override
    public void initViews() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewUsers.setLayoutManager(layoutManager);
        mRecyclerViewUsers.setAdapter(new ProfileCustomAdapter());
        mTextViewName.setText(mName);
        mImageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageViewLike.setVisibility(View.INVISIBLE);
                mImageViewBoom.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_like);
                mImageViewBoom.startAnimation(animation);

                //Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.for_swipe_down);
                //mImageViewAvatar.startAnimation(animation2);

                //Animation animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.white_circle_boom_scale);
                //mImageViewBoomWhiteCircle.setVisibility(View.VISIBLE);
                //mImageViewBoomWhiteCircle.startAnimation(animation3);
            }
        });
    }
}

package com.rockstar.bubblemeetapplication.profile_preview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.OnSwipeTouchListener;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.profile.ProfileFragment;

public class ProfilePreviewFragment extends Fragment implements BaseContract.BaseView {

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
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_profile_preview, null);
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
        mRecyclerViewUsers.setAdapter(new ProfilePreviewCustomAdapter(getContext()));
        mTextViewName.setText(mName);
        mImageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageViewLike.setVisibility(View.INVISIBLE);
                mImageViewBoom.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_like);
                mImageViewBoom.startAnimation(animation);
            }
        });
        mImageViewAvatar.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeTop() {
                //Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
            public void onSwipeRight() {
                //Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                Animation animationBoom = AnimationUtils.loadAnimation(getContext(), R.anim.white_circle_boom_scale);
                animationBoom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //Do nothing
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageViewBoomWhiteCircle.setVisibility(View.GONE);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        ProfileFragment profileFragment = new ProfileFragment();
                        profileFragment.setName(mName);
                        transaction.replace(R.id.root_fragment, profileFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //Do nothing
                    }
                });
                mImageViewBoomWhiteCircle.setVisibility(View.VISIBLE);
                mImageViewBoomWhiteCircle.startAnimation(animationBoom);
            }
            public void onSwipeLeft() {
                //Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                Animation animationBoom = AnimationUtils.loadAnimation(getContext(), R.anim.white_circle_boom_scale);
                animationBoom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //Do nothing
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageViewBoomWhiteCircle.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //Do nothing
                    }
                });
                mImageViewBoomWhiteCircle.setVisibility(View.VISIBLE);
                mImageViewBoomWhiteCircle.startAnimation(animationBoom);
            }
            public void onSwipeBottom() {
                //Toast.makeText(getContext(), "bottom", Toast.LENGTH_SHORT).show();
                Animation animationSwipeDown = AnimationUtils.loadAnimation(getContext(), R.anim.for_swipe_down);
                mImageViewAvatar.startAnimation(animationSwipeDown);
            }

        });
    }
}

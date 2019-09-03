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
    ImageView mImageViewPinkDown1;
    ImageView mImageViewPinkDown2;
    ImageView mImageViewPinkLeftDown;
    ImageView mImageViewPinkRightDown;
    ImageView mImageViewPinkLeftUp;
    ImageView mImageViewPinkRightUp;
    ImageView mImageViewPinkUp1;
    ImageView mImageViewPinkUp2;
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
        mImageViewPinkDown1 = (ImageView) view.findViewById(R.id.imageViewPinkDown1);
        mImageViewPinkDown2 = (ImageView) view.findViewById(R.id.imageViewPinkDown2);
        mImageViewPinkLeftDown = (ImageView) view.findViewById(R.id.imageViewPinkLeftDown);
        mImageViewPinkRightDown = (ImageView) view.findViewById(R.id.imageViewPinkRightDown);
        mImageViewPinkLeftUp = (ImageView) view.findViewById(R.id.imageViewPinkLeftUp);
        mImageViewPinkRightUp = (ImageView) view.findViewById(R.id.imageViewPinkRightUp);
        mImageViewPinkUp1 = (ImageView) view.findViewById(R.id.imageViewPinkUp1);
        mImageViewPinkUp2 = (ImageView) view.findViewById(R.id.imageViewPinkUp2);
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
                Animation animationPinkDown1 = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_down1);
                mImageViewPinkDown1.setVisibility(View.VISIBLE);
                mImageViewPinkDown1.startAnimation(animationPinkDown1);
                Animation animationPinkDown2 = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_down2);
                mImageViewPinkDown2.setVisibility(View.VISIBLE);
                mImageViewPinkDown2.startAnimation(animationPinkDown2);
                Animation animationPinkLeftDown = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_left_down);
                mImageViewPinkLeftDown.setVisibility(View.VISIBLE);
                mImageViewPinkLeftDown.startAnimation(animationPinkLeftDown);
                Animation animationPinkRightDown = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_right_down);
                mImageViewPinkRightDown.setVisibility(View.VISIBLE);
                mImageViewPinkRightDown.startAnimation(animationPinkRightDown);
                Animation animationPinkLeftUp = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_left_up);
                mImageViewPinkLeftUp.setVisibility(View.VISIBLE);
                mImageViewPinkLeftUp.startAnimation(animationPinkLeftUp);
                Animation animationPinkRightUp = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_right_up);
                mImageViewPinkRightUp.setVisibility(View.VISIBLE);
                mImageViewPinkRightUp.startAnimation(animationPinkRightUp);
                Animation animationPinkUp1 = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_up1);
                mImageViewPinkUp1.setVisibility(View.VISIBLE);
                mImageViewPinkUp1.startAnimation(animationPinkUp1);
                Animation animationPinkUp2 = AnimationUtils.loadAnimation(getContext(), R.anim.translate_pink_up2);
                mImageViewPinkUp2.setVisibility(View.VISIBLE);
                mImageViewPinkUp2.startAnimation(animationPinkUp2);
                Animation animationBoom = AnimationUtils.loadAnimation(getContext(), R.anim.white_circle_boom_scale);
                animationBoom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mImageViewAvatar.setVisibility(View.INVISIBLE);
                        Animation animationAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_auth_views);
                        mImageViewAvatar.startAnimation(animationAlpha);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageViewBoomWhiteCircle.setVisibility(View.GONE);
                        mImageViewAvatar.setVisibility(View.VISIBLE);
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

package com.rockstar.bubblemeetapplication.profile_preview;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.OnSwipeTouchListener;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile.ProfileFragment;

import java.util.Calendar;

public class ProfilePreviewFragment extends Fragment implements BaseContract.BaseView {

    boolean doubleTap;
    String mName;
    TextView mTextViewName;
    TextView mTextViewYearsOldAndCity;
    ImageView mImageViewLike;
    ImageView mImageViewBoom;
    ImageView mImageViewAvatar;
    ImageView mImageViewBoomWhiteCircle;
    ImageView mImageViewMessages;

    ImageView mImageViewPinkDown1;
    ImageView mImageViewPinkDown2;
    ImageView mImageViewPinkLeftDown;
    ImageView mImageViewPinkRightDown;
    ImageView mImageViewPinkLeftUp;
    ImageView mImageViewPinkRightUp;
    ImageView mImageViewPinkUp1;
    ImageView mImageViewPinkUp2;

    ImageView mImageViewGrayDown;
    ImageView mImageViewGrayLeft;
    ImageView mImageViewGrayRight;
    ImageView mImageViewGrayUp;
    ImageView mImageViewGrayDownLeft;
    ImageView mImageViewGrayDownRight;
    ImageView mImageViewGrayUpLeft;
    ImageView mImageViewGrayUpRight;

    ImageView mImageViewBubbles;
    RecyclerView mRecyclerViewUsers;

    public void setName(UserData name){
        mName = name.getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.fragment_profile_preview, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).showButtonBack();
        mRecyclerViewUsers = (RecyclerView) view.findViewById(com.rockstar.bubblemeetapplication.R.id.recyclerViewUsers);
        mTextViewName = (TextView) view.findViewById(R.id.textViewName);
        mTextViewYearsOldAndCity = (TextView) view.findViewById(R.id.textViewYearsOldAndCity);
        mImageViewMessages = (ImageView) view.findViewById(R.id.imageViewMessage);
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

        mImageViewGrayDown = (ImageView) view.findViewById(R.id.imageViewGrayDown);
        mImageViewGrayLeft = (ImageView) view.findViewById(R.id.imageViewGrayLeft);
        mImageViewGrayRight = (ImageView) view.findViewById(R.id.imageViewGrayRight);
        mImageViewGrayUp = (ImageView) view.findViewById(R.id.imageViewGrayUp);
        mImageViewGrayDownLeft = (ImageView) view.findViewById(R.id.imageViewGrayDownLeft);
        mImageViewGrayDownRight = (ImageView) view.findViewById(R.id.imageViewGrayDownRight);
        mImageViewGrayUpLeft = (ImageView) view.findViewById(R.id.imageViewGrayUpLeft);
        mImageViewGrayUpRight = (ImageView) view.findViewById(R.id.imageViewGrayUpRight);

        mImageViewBubbles = (ImageView) view.findViewById(R.id.imageViewBubbles);
        initViews();
    }

    @Override
    public void initViews() {
        doubleTap = false;
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
                boomAnimation(true);

            }
            public void onSwipeLeft() {
                boomAnimation(false);
            }
            public void onSwipeBottom() {
                //Toast.makeText(getContext(), "bottom", Toast.LENGTH_SHORT).show();
                Animation animationSwipeDown = AnimationUtils.loadAnimation(getContext(), R.anim.for_swipe_down);
                mImageViewAvatar.startAnimation(animationSwipeDown);
            }

            public void onClick() {
                //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                //boomAnimation(true);
                //mTimeFirstClick = 0;
                final Handler myHandler = new Handler();
                myHandler.postDelayed(new Runnable() {
                    public void run() {
                        if(!doubleTap) {
                            boomAnimation(true);
                        }else{
                            doubleTap = false;
                        }
                    }
                }, 200);
            }

            public void onDoubleClick() {
                doubleTap = true;
                Animation animationAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_profile);
                mImageViewAvatar.startAnimation(animationAlpha);
            }
        });
        final Animation animationAlphaBubbles = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_profile);
        animationAlphaBubbles.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation animationAlphaOtherViews = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_profile);
                mTextViewName.setAnimation(animationAlphaOtherViews);
                mTextViewYearsOldAndCity.setAnimation(animationAlphaOtherViews);
                mImageViewLike.setAnimation(animationAlphaOtherViews);
                mImageViewMessages.setAnimation(animationAlphaOtherViews);
                mImageViewAvatar.setAnimation(animationAlphaOtherViews);
                mRecyclerViewUsers.setAnimation(animationAlphaOtherViews);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //Do nothing
            }
        });
        mImageViewBubbles.setAnimation(animationAlphaBubbles);
    }

    private void boomAnimation(final boolean isRight){
        Animation animationGrayDown = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_down);
        mImageViewGrayDown.setVisibility(View.VISIBLE);
        mImageViewGrayDown.startAnimation(animationGrayDown);
        Animation animationGrayLeft = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_left);
        mImageViewGrayLeft.setVisibility(View.VISIBLE);
        mImageViewGrayLeft.startAnimation(animationGrayLeft);
        Animation animationGrayRight = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_right);
        mImageViewGrayRight.setVisibility(View.VISIBLE);
        mImageViewGrayRight.startAnimation(animationGrayRight);
        Animation animationGrayUp = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_up);
        mImageViewGrayUp.setVisibility(View.VISIBLE);
        mImageViewGrayUp.startAnimation(animationGrayUp);
        Animation animationGrayDownLeft = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_down_left);
        mImageViewGrayDownLeft.setVisibility(View.VISIBLE);
        mImageViewGrayDownLeft.startAnimation(animationGrayDownLeft);
        Animation animationGrayDownRight = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_down_right);
        mImageViewGrayDownRight.setVisibility(View.VISIBLE);
        mImageViewGrayDownRight.startAnimation(animationGrayDownRight);
        Animation animationGrayUpLeft = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_up_left);
        mImageViewGrayUpLeft.setVisibility(View.VISIBLE);
        mImageViewGrayUpLeft.startAnimation(animationGrayUpLeft);
        Animation animationGrayUpRight = AnimationUtils.loadAnimation(getContext(), R.anim.translate_gray_up_right);
        mImageViewGrayUpRight.setVisibility(View.VISIBLE);
        mImageViewGrayUpRight.startAnimation(animationGrayUpRight);

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
                if(isRight){
                    mImageViewBoomWhiteCircle.setVisibility(View.GONE);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setName(mName);
                    transaction.replace(R.id.root_fragment, profileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //Do nothing
            }
        });
        mImageViewBoomWhiteCircle.setVisibility(View.VISIBLE);
        mImageViewBoomWhiteCircle.startAnimation(animationBoom);
    }


}

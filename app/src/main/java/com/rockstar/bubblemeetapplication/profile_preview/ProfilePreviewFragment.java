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

import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.OnSwipeTouchListener;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.bubble.BubbleFragment2;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile.ProfileFragment;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfilePreviewFragment extends Fragment implements BaseContract.BaseView {

    boolean doubleTap;
    UserData mUser;
    TextView mTextViewName;
    TextView mTextViewYearsOldAndCity;
    ImageView mImageViewLike;
    ImageView mImageViewMessages;
    ImageView mImageViewBoom;
    ImageView mImageViewBubbles;
    CircleImageView mImageViewAvatar;
    ShineButton mShineButton;
    RecyclerView mRecyclerViewUsers;

    public void setName(UserData user){
        mUser = user;
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
        mImageViewAvatar = (CircleImageView) view.findViewById(R.id.imageViewAvatar);
        mImageViewBubbles = (ImageView) view.findViewById(R.id.imageViewBubbles);
        mShineButton = (ShineButton) view.findViewById(R.id.imageBoom);
        initViews();
    }

    @Override
    public void initViews() {
        doubleTap = false;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewUsers.setLayoutManager(layoutManager);
        mRecyclerViewUsers.setAdapter(new ProfilePreviewCustomAdapter(getContext()));
        mTextViewName.setText(mUser.getName());
        mImageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageViewLike.setVisibility(View.INVISIBLE);
                mImageViewBoom.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_like);
                mImageViewBoom.startAnimation(animation);
            }
        });

        Picasso.with(getContext())
                .load(mUser.getPhoto())
                .into(mImageViewAvatar);

        mImageViewAvatar.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeTop() {
                //Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
                //getActivity().onBackPressed();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                BubbleFragment2 bubbleFragment = new BubbleFragment2();
                ArrayList<UserData> users = new ArrayList<UserData>();
                for(int i = 0; i < 800; i++){
                    if(i % 2 != 0) {
                        users.add(new UserData(i, "Name" + i, "one's city", "https://i.citrus.ua/uploads/content/product-photos/fedenicheva/April/image.jpg"));
                    }else {
                        users.add(new UserData(i, "Name" + i, "one's city", "https://cdn.wccftech.com/wp-content/uploads/2017/10/WCCFgabenewell-740x429.jpg"));
                    }
                }
                bubbleFragment.setUsers(users);
                transaction.replace(R.id.root_fragment, bubbleFragment);
                //transaction.addToBackStack(null);
                //transaction.addToBackStack(null);
                transaction.commit();
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
                animationSwipeDown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageViewAvatar.animate()
                                .translationY(1)
                                .translationY(-1);
                        Animation animationAlphaOtherViews = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_profile);
                        mImageViewAvatar.setAnimation(animationAlphaOtherViews);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            public void onClick() {
                //boomAnimation(true);
                final Handler myHandler = new Handler();
                myHandler.postDelayed(new Runnable() {
                    public void run() {
                        if(!doubleTap) {
                            //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                            boomAnimation(true);
                        }else{
                            doubleTap = false;
                        }
                    }
                }, 200);
            }

            public void onDoubleClick() {
                doubleTap = true;
                //Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                //Animation animationAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_profile);
                //mImageViewAvatar.startAnimation(animationAlpha);
                //boomAnimation(false);
                boomAnimation(false);

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

    private void boomAnimation(boolean isRight){
        mShineButton.performClick();
        mShineButton.setChecked(false);
        if(isRight){
            final Handler myHandler = new Handler();
            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setUser(mUser);
                    transaction.replace(R.id.root_fragment, profileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }, 1000);
        }else{
            final Handler myHandler = new Handler();
            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
                    profileFragment.setName(new UserData(0, "Elon Musk", "Mars", "https://24smi.org/public/media/celebrity/2017/10/10/ATIfxWH4rRO0_ilon-mask.jpg"));
                    transaction.replace(R.id.root_fragment, profileFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }, 1000);
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

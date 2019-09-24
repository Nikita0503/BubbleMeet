package com.rockstar.bubblemeetapplication.bubble;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.CircleTransform;
import com.rockstar.bubblemeetapplication.CircularTransformation;
import com.rockstar.bubblemeetapplication.R;
import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import retrofit2.http.PATCH;

public class BubbleFragment2 extends Fragment implements BaseContract.BaseView {

    private static final int MAX_CLICK_DURATION = 75;
    private static final int MAX_MOVEMENT_DIFFERENCE = 20;
    private boolean isSlowed;
    private boolean isConnectTop;
    private boolean isConnectLeft;
    private boolean isConnectBottom;
    private boolean isConnectRight;
    private boolean isXMoving;
    private boolean isYMoving;
    private boolean isMoving;
    private boolean isTopSideMoving;
    private boolean isRightSideMoving;
    private int mDifferenceX;
    private int mDifferenceY;
    private int mDisplayCenterX;
    private int mDisplayCenterY;
    private int mDefaultBubbleDiameter;
    private int mRows;
    private long mStartClickTime;
    private long mPreviousMoveTime;
    private int[] mDefaultYMinSize;
    private int[] mDefaultYMaxSize;
    private int[] mXPrevious;
    private int[] mYPrevious;
    private int[] mDiameterPrevious;
    //private String[] mUsers;
    private ArrayList<UserData> mUsers;
    private AbsoluteLayout mLayout;

    public void setUsers(ArrayList<UserData> users){
        mUsers = users;

    }

    //public void setUsers(String[] users){
    //    mUsers = users;
    //    mDefaultYMinSize = new int[mUsers.length];
    //    mDefaultYMaxSize = new int[mUsers.length];
    //    mXPrevious = new int[mUsers.length];
    //    mYPrevious = new int[mUsers.length];
    //    mDiameterPrevious = new int[mUsers.length];
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bubble_grid_view2, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mLayout = (AbsoluteLayout) view.findViewById(R.id.layout);
        Log.d("ViewCreated", "+");
        mDefaultYMinSize = new int[mUsers.size()];
        mDefaultYMaxSize = new int[mUsers.size()];
        mXPrevious = new int[mUsers.size()];
        mYPrevious = new int[mUsers.size()];
        mDiameterPrevious = new int[mUsers.size()];
        initViews();
    }

    @Override
    public void initViews() {
        MainActivity activity = (MainActivity) getActivity();
        activity.hideButtonBack();
        activity.showButtonFilters();
        activity.resetMenuIcons();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mDefaultBubbleDiameter = (int) (size.x / 2.6);
        mDisplayCenterX = (size.x / 2);
        mDisplayCenterX -= mDisplayCenterX/21;
        mDisplayCenterY = size.y / 2 - size.y / 10;
        mRows = (int) Math.sqrt(mUsers.size());
        Log.d("sqrt", "sqrt = " + mRows);
        Log.d("centerX", "x = " + mDisplayCenterX);
        Log.d("centerY", "y = " + mDisplayCenterY);
        for(int i = 0; i < mRows; i++){
            for(int j = 0; j < mRows; j++){
                ImageView imageViewBubble = new ImageView(getContext());
                AbsoluteLayout.LayoutParams params
                        = new AbsoluteLayout.LayoutParams(mDefaultBubbleDiameter, mDefaultBubbleDiameter, j*(int) (size.x / 2.9),  i*(int) (size.x / 2.6));
                if(i % 2 != 0) {
                    params.x += (int) (size.x / 2.9) / 2;
                }
                imageViewBubble.setLayoutParams(params);
                mLayout.addView(imageViewBubble);
            }
        }
        for(int i = 0; i < mLayout.getChildCount(); i++){
            Picasso.with(getContext()).load(mUsers.get(i).getPhoto()).transform(new CircleTransform()).into((ImageView) mLayout.getChildAt(i));
        }
        for(int i = 0; i < mLayout.getChildCount(); i++){
            AbsoluteLayout.LayoutParams paramsBubbleNew = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
            paramsBubbleNew.x += mDefaultBubbleDiameter * (-mRows/2);
            paramsBubbleNew.y += mDefaultBubbleDiameter * (-mRows/2);
        }

        for(int i = 0; i < mLayout.getChildCount(); i++){
            AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
            mDefaultYMaxSize[i] = paramsBubble.y - (mDefaultBubbleDiameter/2);
            mDefaultYMinSize[i] = paramsBubble.y ;

        }
        for(int i = 0; i < 10; i++) {
            start();
        }

        Log.d("bubbles", mUsers.size()+"");
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, final MotionEvent event) {
                for(int i = 0; i < mLayout.getChildCount(); i++){
                    AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isMoving = false;
                            mStartClickTime = Calendar.getInstance().getTimeInMillis();
                            mPreviousMoveTime = Calendar.getInstance().getTimeInMillis();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            mPreviousMoveTime = Calendar.getInstance().getTimeInMillis();
                            update(i, paramsBubble, event);
                            break;
                        case MotionEvent.ACTION_UP:
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - mStartClickTime;
                            if (clickDuration < MAX_CLICK_DURATION) {
                                isBubble(event);
                            }
                            long movementDifference = Calendar.getInstance().getTimeInMillis() - mPreviousMoveTime;
                            if(movementDifference < MAX_MOVEMENT_DIFFERENCE) {
                                isMoving = true;
                                mDifferenceX /= 2;
                                mDifferenceY /= 2;
                                //mDifferenceX = getAverage(mWayDifferencesX);
                                //mDifferenceY = getAverage(mWayDifferencesY);
                                if(Math.abs(mDifferenceX) > 2 && Math.abs(mDifferenceY) > 2) {
                                    if (mDifferenceX > 40) {
                                        mDifferenceX = 40;
                                    } else {
                                        if (mDifferenceX >= 0 && mDifferenceX < 15) {
                                            mDifferenceX = 15;
                                        }
                                    }
                                    if (mDifferenceY > 40) {
                                        mDifferenceY = 40;
                                    } else {
                                        if (mDifferenceY >= 0 && mDifferenceY < 15) {
                                            mDifferenceY = 15;
                                        }
                                    }
                                    if (mDifferenceX < -40) {
                                        mDifferenceX = -40;
                                    } else {
                                        if (mDifferenceX > -15 && mDifferenceX <= 0) {
                                            mDifferenceX = -15;
                                        }
                                    }
                                    if (mDifferenceY < -40) {
                                        mDifferenceY = -40;
                                    } else {
                                        if (mDifferenceY > -15 && mDifferenceY <= 0) {
                                            mDifferenceY = -15;
                                        }
                                    }

                                }
                                inertia();
                            }
                            return true;
                        case MotionEvent.ACTION_CANCEL:

                            break;
                    }
                    mXPrevious[i] = Math.round(event.getX());
                    mYPrevious[i] = Math.round(event.getY());
                    mDiameterPrevious[i] = paramsBubble.height;
                }
                Log.d("event", event.toString());
                return true;
            }
        });
    }

    private void isBubble(MotionEvent event){
        for(int i = 0; i < mLayout.getChildCount(); i++){
            AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
            if(event.getX() > paramsBubble.x
                    && event.getY() > paramsBubble.y
                    && event.getX() < (paramsBubble.x + (paramsBubble.height))
                    && event.getY() < (paramsBubble.y + (paramsBubble.height))){
                Log.d("sizeWidth", paramsBubble.width + "");
                Log.d("sizeHeight", paramsBubble.height + "");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
                profileFragment.setName(mUsers.get(i));
                transaction.replace(R.id.root_fragment, profileFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
        }
    }

    private void update(int i, AbsoluteLayout.LayoutParams paramsBubble, MotionEvent event){
        if (mXPrevious[i] > 0 && mXPrevious[i] < (mDisplayCenterX * 2)) {
            if (mXPrevious[i] != 0 && mYPrevious[i] != 0) {
                mDifferenceX = Math.round(event.getX() - mXPrevious[i]);
                mDifferenceY = Math.round(event.getY() - mYPrevious[i]);
                if(mDifferenceX < 0){
                    isRightSideMoving = true;
                    isXMoving = true;
                }else{
                    isRightSideMoving = false;
                    isXMoving = true;
                }
                if(mDifferenceY > 0){
                    isTopSideMoving = true;
                    isYMoving = true;
                }else{
                    isTopSideMoving = false;
                    isYMoving = true;
                }

                if(i == 6) {
                    //mWayDifferencesX.add(mDifferenceX);
                    //mWayDifferencesY.add(mDifferenceY);
                    Log.d("differenceX", mDifferenceX + "");
                    Log.d("differenceY", mDifferenceY + "");
                    Log.d("differenceY", " ");
                }

                //BORDERING
                AbsoluteLayout.LayoutParams paramsLeftTopBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(0).getLayoutParams();
                if(paramsLeftTopBorderBubble.x > mDefaultBubbleDiameter / 2) {
                    isConnectLeft = true;
                }else{
                    isConnectLeft = false;
                }
                if(paramsLeftTopBorderBubble.y > mDefaultBubbleDiameter){
                    isConnectTop = true;
                }else{
                    isConnectTop = false;
                }
                AbsoluteLayout.LayoutParams paramsRightBottomBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(mLayout.getChildCount()-1).getLayoutParams();
                if(paramsRightBottomBorderBubble.x < (mDisplayCenterX - (mDefaultBubbleDiameter / 2))){
                    isConnectRight = true;
                }else{
                    isConnectRight = false;
                }
                if(paramsRightBottomBorderBubble.y < mDisplayCenterY){
                    isConnectBottom = true;
                }else {
                    isConnectBottom = false;
                }
                ////BORDERING

                //SCALING
                float multiply = 0;
                float pixelsToSideFromBubbleX = 0;
                float toSideFromBubblePercentX = 0;
                float pixelsToSideFromBubbleY = 0;
                float toSideFromBubblePercentY = 0;
                //if (paramsBubble.x < mDisplayCenterX - mDefaultBubbleDiameter / 3) {
                //    pixelsToSideFromBubbleX = paramsBubble.x + mDefaultBubbleDiameter / 3;
                //} else {
                //    pixelsToSideFromBubbleX = mDisplayCenterX * 2 - paramsBubble.x - mDefaultBubbleDiameter / 2;
                //}
                //if (paramsBubble.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)) {
                //    pixelsToSideFromBubbleY = paramsBubble.y;
                //} else {
                //    pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter / 3;
                //}
                //if (pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                //    toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
                //    toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                //} else {
                //    toSideFromBubblePercentX = 0;
                //    toSideFromBubblePercentY = 0;
                //}
                //if (toSideFromBubblePercentX > toSideFromBubblePercentY) {
                //    multiply = toSideFromBubblePercentY;
                //    if (toSideFromBubblePercentY < 1) {
                //        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                //    } else {
                //        if (toSideFromBubblePercentY < 0) {
                //
                //            paramsBubble.height = 0;
                //        } else {
                //
                //            paramsBubble.height = mDefaultBubbleDiameter;
                //        }
                //    }
                //} else {
                //    multiply = toSideFromBubblePercentX;
                //    if (toSideFromBubblePercentX < 1) {
                //        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                //    } else {
                //        if (toSideFromBubblePercentX < 0) {
                //
                //            paramsBubble.height = 0;
                //        } else {
                //            paramsBubble.height = mDefaultBubbleDiameter;
                //        }
                //    }
                //}
                boolean isGoingTop;
                if (paramsBubble.x + mDefaultBubbleDiameter/2.5f  < mDisplayCenterX) {
                    pixelsToSideFromBubbleX = paramsBubble.x + mDefaultBubbleDiameter/2.5f;
                } else {
                    pixelsToSideFromBubbleX = mDisplayCenterX * 2 - paramsBubble.x - mDefaultBubbleDiameter/2.5f;
                }
                if (paramsBubble.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)) {
                    isGoingTop = true;
                    pixelsToSideFromBubbleY = paramsBubble.y;
                } else {
                    isGoingTop = false;
                    pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter/2;
                    //pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter / 3;
                }
                if (pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                    toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX);
                    if(isGoingTop) {
                        toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                        if(toSideFromBubblePercentY < 0){
                            toSideFromBubblePercentY = 0;
                        }
                    }else{
                        toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY);
                    }
                } else {
                    toSideFromBubblePercentX = 0;
                    toSideFromBubblePercentY = 0;
                }
                if (toSideFromBubblePercentX > toSideFromBubblePercentY) {
                    multiply = toSideFromBubblePercentY;
                    if (toSideFromBubblePercentY < 1) {
                        if(toSideFromBubblePercentY > 0.5) {
                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                        }else{
                            float percent = toSideFromBubblePercentY * 2;
                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY * percent) * mDefaultBubbleDiameter);
                        }
                    } else {
                        if (toSideFromBubblePercentY < 0) {
                            paramsBubble.height = 0;
                        } else {
                            paramsBubble.height = mDefaultBubbleDiameter;
                        }
                    }
                } else {
                    multiply = toSideFromBubblePercentX;
                    if (toSideFromBubblePercentX < 1) {
                        if(toSideFromBubblePercentX > 0.5) {
                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                        }else{
                            float percent = toSideFromBubblePercentX * 2;
                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX * percent) * mDefaultBubbleDiameter);
                        }
                    } else {
                        if (toSideFromBubblePercentX < 0) {
                            paramsBubble.height = 0;
                        } else {
                            paramsBubble.height = mDefaultBubbleDiameter;
                        }
                    }
                }

                if(multiply > 1) multiply = 1;
                //SCALING

                //MOVING
                if(!isConnectBottom && !isConnectRight && !isConnectTop && !isConnectLeft){
                    paramsBubble.x += mDifferenceX;
                    paramsBubble.y += mDifferenceY;
                    mDefaultYMinSize[i] += mDifferenceY;
                    mDefaultYMaxSize[i] += mDifferenceY;
                    if(multiply > 0 && multiply < 1) {
                        paramsBubble.y += (mDiameterPrevious[i]-paramsBubble.height)/2;
                        //paramsBubble.y = (int) (defaultYMax[i] + (mDefaultBubbleDiameter * round(multiply, 2) / 2));
                        if(i==6) {
                            Log.d("multiply", multiply + "");
                        }
                    }
                    if (multiply == 0) {
                        paramsBubble.y = mDefaultYMinSize[i];

                    }
                    if( multiply == 1){
                        paramsBubble.y = mDefaultYMaxSize[i];
                    }
                }else{
                    if(isConnectBottom) {
                        if (isTopSideMoving) {
                            paramsBubble.y += mDifferenceY;
                            mDefaultYMinSize[i] += mDifferenceY;
                            mDefaultYMaxSize[i] += mDifferenceY;
                        }
                    }
                    if(isConnectRight) {
                        if (!isRightSideMoving) {
                            paramsBubble.x += mDifferenceX;
                        }
                    }
                    if(isConnectTop) {
                        if (!isTopSideMoving){
                            paramsBubble.y += mDifferenceY;
                            mDefaultYMinSize[i] += mDifferenceY;
                            mDefaultYMaxSize[i] += mDifferenceY;
                        }
                    }
                    if(isConnectLeft) {
                        if (isRightSideMoving){
                            paramsBubble.x += mDifferenceX;
                        }
                    }
                }
                //MOVING
            }
        }
        mLayout.getChildAt(i).setLayoutParams(paramsBubble);
    }


    private void start(){
        for(int i = 0; i < mLayout.getChildCount(); i++){
            AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();

            if(i == 6){
                Log.d("differenceX", mDifferenceX + "");
                Log.d("differenceY", mDifferenceY + "");
                Log.d("differenceY", " ");
            }

            //BORDERING
            AbsoluteLayout.LayoutParams paramsLeftTopBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(0).getLayoutParams();
            if (paramsLeftTopBorderBubble.x > mDefaultBubbleDiameter / 2) {
                isConnectLeft = true;
            }else{
                isConnectLeft = false;
            }
            if(paramsLeftTopBorderBubble.y > mDefaultBubbleDiameter){
                isConnectTop = true;
            }else{
                isConnectTop = false;
            }
            AbsoluteLayout.LayoutParams paramsRightBottomBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(mLayout.getChildCount()-1).getLayoutParams();
            if(paramsRightBottomBorderBubble.x < (mDisplayCenterX - (mDefaultBubbleDiameter / 2))){
                isConnectRight = true;
            }else{
                isConnectRight = false;
            }
            if(paramsRightBottomBorderBubble.y < mDisplayCenterY){
                isConnectBottom = true;
            }else {
                isConnectBottom = false;
            }
            //BORDERING

            //SCALING
            float multiply = 0;
            float pixelsToSideFromBubbleX = 0;
            float toSideFromBubblePercentX = 0;
            float pixelsToSideFromBubbleY = 0;
            float toSideFromBubblePercentY = 0;
            boolean isGoingTop;
            if (paramsBubble.x + mDefaultBubbleDiameter/2.5f  < mDisplayCenterX) {
                pixelsToSideFromBubbleX = paramsBubble.x + mDefaultBubbleDiameter/2.5f;
            } else {
                pixelsToSideFromBubbleX = mDisplayCenterX * 2 - paramsBubble.x - mDefaultBubbleDiameter/2.5f;
            }
            if (paramsBubble.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)) {
                isGoingTop = true;
                pixelsToSideFromBubbleY = paramsBubble.y;
            } else {
                isGoingTop = false;
                pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter/2;
                //pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter / 3;
            }
            if (pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX);
                if(isGoingTop) {
                    toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                    if(toSideFromBubblePercentY < 0){
                        toSideFromBubblePercentY = 0;
                    }
                }else{
                    toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY);
                }

            } else {
                toSideFromBubblePercentX = 0;
                toSideFromBubblePercentY = 0;
            }
            if (toSideFromBubblePercentX > toSideFromBubblePercentY) {
                multiply = toSideFromBubblePercentY;
                if (toSideFromBubblePercentY < 1) {
                    if(toSideFromBubblePercentY > 0.5) {
                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                    }else{
                        float percent = toSideFromBubblePercentY * 2;
                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY * percent) * mDefaultBubbleDiameter);
                    }
                } else {
                    if (toSideFromBubblePercentY < 0) {
                        paramsBubble.height = 0;
                    } else {
                        paramsBubble.height = mDefaultBubbleDiameter;
                    }
                }
            } else {
                multiply = toSideFromBubblePercentX;
                if (toSideFromBubblePercentX < 1) {
                    if(toSideFromBubblePercentX > 0.5) {
                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                    }else{
                        float percent = toSideFromBubblePercentX * 2;
                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX * percent) * mDefaultBubbleDiameter);
                    }
                } else {
                    if (toSideFromBubblePercentX < 0) {
                        paramsBubble.height = 0;
                    } else {
                        paramsBubble.height = mDefaultBubbleDiameter;
                    }
                }
            }

            if(multiply > 1) multiply = 1;
            //SCALING

            //MOVING
            if(!isConnectBottom && !isConnectRight && !isConnectTop && !isConnectLeft){
                paramsBubble.x += mDifferenceX;
                paramsBubble.y += mDifferenceY;
                mDefaultYMinSize[i] += mDifferenceY;
                mDefaultYMaxSize[i] += mDifferenceY;
                if(multiply > 0 && multiply < 1) {
                    paramsBubble.y += (mDiameterPrevious[i]-paramsBubble.height)/2;
                    //paramsBubble.y = (int) (defaultYMax[i] + (mDefaultBubbleDiameter * round(multiply, 2) / 2));
                    if(i==6) {
                        Log.d("multiply", multiply + "");
                    }
                }
                if (multiply == 0) {
                    paramsBubble.y = mDefaultYMinSize[i];

                }
                if( multiply == 1){
                    paramsBubble.y = mDefaultYMaxSize[i];
                }
            }else{
                if(isConnectBottom) {
                    if (isTopSideMoving) {
                        paramsBubble.y += mDifferenceY;
                        mDefaultYMinSize[i] += mDifferenceY;
                        mDefaultYMaxSize[i] += mDifferenceY;
                    }
                }
                if(isConnectRight) {
                    if (!isRightSideMoving) {
                        paramsBubble.x += mDifferenceX;
                    }
                }
                if(isConnectTop) {
                    if (!isTopSideMoving){
                        paramsBubble.y += mDifferenceY;
                        mDefaultYMinSize[i] += mDifferenceY;
                        mDefaultYMaxSize[i] += mDifferenceY;
                    }
                }
                if(isConnectLeft) {
                    if (isRightSideMoving){
                        paramsBubble.x += mDifferenceX;
                    }
                }
            }
            //MOVING

            mLayout.getChildAt(i).setLayoutParams(paramsBubble);
            mDiameterPrevious[i] = paramsBubble.height;
        }
    }

    private void inertia(){
        final Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            public void run() {
                //if(!isXMoving && !isYMoving){
                //    isMoving = false;
                //}
                if(isMoving) {
                    //if (isRightSideMoving) {
                    //    if (mDifferenceX < 0) {
                    //        if(isSlowed) {
                    //            mDifferenceX += 1;
                    //            isSlowed = false;
                    //        }else{
                    //            isSlowed = true;
                    //        }
                    //    }else{
                    //        isXMoving = false;
                    //    }
                    //} else {
                    //    if (mDifferenceX > 0) {
                    //        if(isSlowed) {
                    //            mDifferenceX -= 1;
                    //            isSlowed = false;
                    //        }else{
                    //            isSlowed = true;
                    //        }
                    //    }else{
                    //        isXMoving = false;
                    //    }
                    //}
                    //if(isTopSideMoving){
                    //    if(mDifferenceY > 0){
                    //        if(isSlowed){
                    //            mDifferenceY -= 1;
                    //            isSlowed = false;
                    //        }else{
                    //            isSlowed = true;
                    //        }
                    //    }else{
                    //        isYMoving = false;
                    //    }
                    //}else{
                    //    if(mDifferenceY < 0){
                    //        if(isSlowed){
                    //            mDifferenceY += 1;
                    //            isSlowed = false;
                    //        }else{
                    //            isSlowed = true;
                    //        }
                    //    }else{
                    //        isYMoving = false;
                    //    }
                    //}


                    //if(mDifferenceX == 0 && mDifferenceY == 0){
                    //    isMoving = false;
                    //}
                    //if(isRightSideMoving && isTopSideMoving){
                    //    mDifferenceX += 1;
                    //    mDifferenceY -= 1;
                    //}
                    //if(isRightSideMoving && !isTopSideMoving){
                    //    mDifferenceX += 1;
                    //    mDifferenceY += 1;
                    //}
                    //if(!isRightSideMoving && isTopSideMoving){
                    //    mDifferenceX -= 1;
                    //    mDifferenceY -= 1;
                    //}
                    //if(!isRightSideMoving && !isTopSideMoving){
                    //    mDifferenceX -= 1;
                    //    mDifferenceY += 1;
                    //}

                    if(mDifferenceX == 0 && mDifferenceY == 0){
                        isMoving = false;
                    }
                    if(mDifferenceX < 0 && mDifferenceY > 0){
                        if(isSlowed) {
                            mDifferenceX++;
                            mDifferenceY--;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceX < 0 && mDifferenceY < 0){
                        if(isSlowed) {
                            mDifferenceX++;
                            mDifferenceY++;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceX > 0 && mDifferenceY > 0){
                        if(isSlowed) {
                            mDifferenceX--;
                            mDifferenceY--;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceX > 0 && mDifferenceY < 0){
                        if(isSlowed) {
                            mDifferenceX--;
                            mDifferenceY++;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceX > 0 && mDifferenceY == 0){
                        if(isSlowed) {
                            mDifferenceX--;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceX < 0 && mDifferenceY == 0){
                        if(isSlowed) {
                            mDifferenceX++;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceY > 0 && mDifferenceX == 0){
                        if(isSlowed) {
                            mDifferenceY--;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                    }
                    if(mDifferenceY < 0 && mDifferenceX == 0){
                        if(isSlowed) {
                            mDifferenceY++;
                            isSlowed = false;
                        }else{
                            isSlowed = true;
                        }
                     }
                    start();
                    myHandler.postDelayed(this, 5);
                }else{
                    //mWayDifferencesX.clear();
                    //mWayDifferencesY.clear();
                }
            }
        }, 5);
    }

}
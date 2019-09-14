package com.rockstar.bubblemeetapplication.bubble;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class BubbleFragment2 extends Fragment implements BaseContract.BaseView {

    private static final int MAX_CLICK_DURATION = 100;
    private int mStartX;
    private int mStartY;
    private int mDisplayCenterX;
    private int mDisplayCenterY;
    private int mDefaultBubbleDiameter;
    private int mRows;
    private long mStartClickTime;
    private int[] mXPrevious;
    private int[] mYPrevious;
    private String[] mUsers;
    private AbsoluteLayout mLayout;

    public void setUsers(String[] users){
        mUsers = users;
        mXPrevious = new int[mUsers.length];
        mYPrevious = new int[mUsers.length];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bubble_grid_view2, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mLayout = (AbsoluteLayout) view.findViewById(R.id.layout);

        initViews();
    }

    @Override
    public void initViews() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mDefaultBubbleDiameter = 360;
        mDisplayCenterX = size.x / 2;
        mDisplayCenterY = size.y / 2 - size.y / 10;
        mRows = (int) Math.sqrt(mUsers.length);
        Log.d("sqrt", "sqrt = " + mRows);
        Log.d("centerX", "x = " + mDisplayCenterX);
        Log.d("centerY", "y = " + mDisplayCenterY);

        for(int i = 0; i < mRows; i++){
            for(int j = 0; j < mRows; j++){
                CircleImageView imageViewBubble = new CircleImageView(getContext());
                imageViewBubble.setStrokeWidth(10);
                imageViewBubble.setStrokeColor(getResources().getColor(R.color.colorAroundAvatar));
                imageViewBubble.setImageDrawable(getResources().getDrawable(R.drawable.pudge));
                AbsoluteLayout.LayoutParams params
                        = new AbsoluteLayout.LayoutParams(mDefaultBubbleDiameter, mDefaultBubbleDiameter, j*450,  i*450);
                if(i % 2 != 0){
                    params.x += 225;
                }
                imageViewBubble.setLayoutParams(params);
                mLayout.addView(imageViewBubble);
            }
        }

        Log.d("bubbles", mUsers.length+"");
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                for(int i = 0; i < mLayout.getChildCount(); i++){
                    AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (mXPrevious[i] > 0 && mXPrevious[i] < (mDisplayCenterX * 2)) {
                                if (mXPrevious[i] != 0 && mYPrevious[i] != 0) {
                                    int differenceX = (int) event.getX() - mXPrevious[i];
                                    int differenceY = (int) event.getY() - mYPrevious[i];
                                    //MOVING
                                    paramsBubble.x += differenceX;
                                    paramsBubble.y += differenceY;
                                    //MOVING

                                    //BORDERING
                                    AbsoluteLayout.LayoutParams paramsLeftTopBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(0).getLayoutParams();
                                    if (paramsLeftTopBorderBubble.x > mDefaultBubbleDiameter / 2) {
                                        for (int j = 0; j < mLayout.getChildCount(); j++) {
                                            AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                                            params.x -= differenceX;
                                            mLayout.getChildAt(j).setLayoutParams(params);
                                        }
                                    }
                                    if(paramsLeftTopBorderBubble.y > mDefaultBubbleDiameter){
                                        for (int j = 0; j < mLayout.getChildCount(); j++) {
                                            AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                                            params.y -= differenceY;
                                            mLayout.getChildAt(j).setLayoutParams(params);
                                        }
                                    }

                                    AbsoluteLayout.LayoutParams paramsRightBottomBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(mLayout.getChildCount()-1).getLayoutParams();
                                    if(paramsRightBottomBorderBubble.x < (mDisplayCenterX - (mDefaultBubbleDiameter / 2))){
                                        for (int j = 0; j < mLayout.getChildCount(); j++) {
                                            AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                                            params.x -= differenceX;
                                            mLayout.getChildAt(j).setLayoutParams(params);
                                        }
                                    }
                                    if(paramsRightBottomBorderBubble.y < mDisplayCenterY){
                                        for (int j = 0; j < mLayout.getChildCount(); j++) {
                                            AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                                            params.y -= differenceY;
                                            mLayout.getChildAt(j).setLayoutParams(params);
                                        }
                                    }
                                    //BORDERING

                                    //SCALE
                                    double pixelsToSideFromBubbleX = 0;
                                    double toSideFromBubblePercentX = 0;
                                    double pixelsToSideFromBubbleY = 0;
                                    double toSideFromBubblePercentY = 0;
                                    if(paramsBubble.x < mDisplayCenterX - mDefaultBubbleDiameter / 3){
                                        pixelsToSideFromBubbleX = paramsBubble.x + mDefaultBubbleDiameter / 3;
                                    }else{
                                        pixelsToSideFromBubbleX = mDisplayCenterX * 2 - paramsBubble.x - mDefaultBubbleDiameter / 2;
                                    }
                                    if(paramsBubble.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)){
                                        pixelsToSideFromBubbleY = paramsBubble.y;
                                    }else{
                                        pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter / 3;
                                    }
                                    if(i == 2) {
                                        Log.d("xToSide", pixelsToSideFromBubbleX + "");
                                        Log.d("yToSide", pixelsToSideFromBubbleY + "");
                                    }
                                    if(pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                                        toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
                                        toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                                    }else{
                                        toSideFromBubblePercentX = 0;
                                        toSideFromBubblePercentY = 0;
                                    }
                                    if(toSideFromBubblePercentX > toSideFromBubblePercentY){
                                        if(toSideFromBubblePercentY < 1){
                                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                                        }else{
                                            if(toSideFromBubblePercentY < 0){
                                                paramsBubble.height = 0;
                                            }else {
                                                paramsBubble.height = mDefaultBubbleDiameter;
                                            }
                                        }
                                    }else {
                                        if(toSideFromBubblePercentX < 1){
                                            paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                                        }else{
                                            if(toSideFromBubblePercentX < 0){
                                                paramsBubble.height = 0;
                                            }else {
                                                paramsBubble.height = mDefaultBubbleDiameter;
                                            }
                                        }
                                    }
                                    //SCALE
                                }
                            }
                            mLayout.getChildAt(i).setLayoutParams(paramsBubble);
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                    }
                    mXPrevious[i] = (int) event.getX();
                    mYPrevious[i] = (int) event.getY();
                }
                return true;
            }
        });


        for(int i = 0; i < mLayout.getChildCount(); i++){
            final int finalI = i;
            mLayout.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_MOVE:
                            for(int j = 0; j <  mLayout.getChildCount(); j++) {
                                AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                                int differenceX = mStartX - (int) event.getX();
                                int differenceY = mStartY - (int) event.getY();
                                //MOVING
                                paramsBubble.x -= differenceX;
                                paramsBubble.y -= differenceY;
                                //MOVING

                                //BORDERING
                                AbsoluteLayout.LayoutParams paramsLeftTopBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(0).getLayoutParams();
                                //if (paramsLeftTopBorderBubble.x > mDefaultBubbleDiameter / 2) {
                                //    for (int z = 0; z < mLayout.getChildCount(); z++) {
                                //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(z).getLayoutParams();
                                //        params.x -= differenceX;
                                //        mLayout.getChildAt(z).setLayoutParams(params);
                                //    }
                                //}
                                //if(paramsLeftTopBorderBubble.y > mDefaultBubbleDiameter){
                                //    for (int z = 0; z < mLayout.getChildCount(); z++) {
                                //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(z).getLayoutParams();
                                //        params.y -= differenceY;
                                //        mLayout.getChildAt(z).setLayoutParams(params);
                                //    }
                                //}
                                //AbsoluteLayout.LayoutParams paramsRightBottomBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(mLayout.getChildCount()-1).getLayoutParams();
                                //if(paramsRightBottomBorderBubble.x < (mDisplayCenterX - (mDefaultBubbleDiameter / 2))){
                                //    for (int z = 0; z < mLayout.getChildCount(); z++) {
                                //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(z).getLayoutParams();
                                //        params.x -= differenceX;
                                //        mLayout.getChildAt(z).setLayoutParams(params);
                                //    }
                                //}
                                //if(paramsRightBottomBorderBubble.y < mDisplayCenterY){
                                //    for (int z = 0; z < mLayout.getChildCount(); z++) {
                                //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(z).getLayoutParams();
                                //        params.y -= differenceY;
                                //        mLayout.getChildAt(z).setLayoutParams(params);
                                //    }
                                //}
                                //BORDERING

                                //SCALE
                                double pixelsToSideFromBubbleX = 0;
                                double toSideFromBubblePercentX = 0;
                                double pixelsToSideFromBubbleY = 0;
                                double toSideFromBubblePercentY = 0;
                                if(paramsBubble.x < mDisplayCenterX - mDefaultBubbleDiameter / 3){
                                    pixelsToSideFromBubbleX = paramsBubble.x + mDefaultBubbleDiameter / 3;
                                }else{
                                    pixelsToSideFromBubbleX = mDisplayCenterX * 2 - paramsBubble.x - mDefaultBubbleDiameter / 2;
                                }
                                if(paramsBubble.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)){
                                    pixelsToSideFromBubbleY = paramsBubble.y;
                                }else{
                                    pixelsToSideFromBubbleY = mDisplayCenterY * 2 - paramsBubble.y - mDefaultBubbleDiameter / 3;
                                }
                                if(j == 2) {
                                    Log.d("xToSide", pixelsToSideFromBubbleX + "");
                                    Log.d("yToSide", pixelsToSideFromBubbleY + "");
                                }
                                if(pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                                    toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
                                    toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                                }else{
                                    toSideFromBubblePercentX = 0;
                                    toSideFromBubblePercentY = 0;
                                }
                                if(toSideFromBubblePercentX > toSideFromBubblePercentY){
                                    if(toSideFromBubblePercentY < 1){
                                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                                    }else{
                                        if(toSideFromBubblePercentY < 0){
                                            paramsBubble.height = 0;
                                        }else {
                                            paramsBubble.height = mDefaultBubbleDiameter;
                                        }
                                    }
                                }else {
                                    if(toSideFromBubblePercentX < 1){
                                        paramsBubble.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                                    }else{
                                        if(toSideFromBubblePercentX < 0){
                                            paramsBubble.height = 0;
                                        }else {
                                            paramsBubble.height = mDefaultBubbleDiameter;
                                        }
                                    }
                                }
                                //SCALE

                                mLayout.getChildAt(j).setLayoutParams(paramsBubble);


                                mXPrevious[j] = paramsBubble.x;
                                mYPrevious[j] = paramsBubble.y;
                            }
                            break;
                        case MotionEvent.ACTION_DOWN:
                            mStartClickTime = Calendar.getInstance().getTimeInMillis();
                            mStartX = (int) event.getX();
                            mStartY = (int) event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - mStartClickTime;
                            if (clickDuration < MAX_CLICK_DURATION) {
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
                                profileFragment.setName(new UserData(1, "Name", "City", "ImageURL"));
                                transaction.replace(R.id.root_fragment, profileFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                            break;
                    }

                    return true;
                }
            });
        }


    }
}

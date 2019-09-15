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

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.CircleTransform;
import com.rockstar.bubblemeetapplication.R;
import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import retrofit2.http.PATCH;

public class BubbleFragment2 extends Fragment implements BaseContract.BaseView {

    private static final int MAX_CLICK_DURATION = 100;
    private int mDifferenceX;
    private int mDifferenceY;
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
                ImageView imageViewBubble = new ImageView(getContext());
                //imageViewBubble.setImageDrawable(getResources().getDrawable(R.drawable.circle_for_photo_gray));

                AbsoluteLayout.LayoutParams params
                        = new AbsoluteLayout.LayoutParams(mDefaultBubbleDiameter, mDefaultBubbleDiameter, j*450,  i*450);
                if(i % 2 != 0) {
                    Picasso.with(getContext()).load("https://i.citrus.ua/uploads/content/product-photos/fedenicheva/April/image.jpg").transform(new CircleTransform()).into(imageViewBubble);
                    params.x += 225;
                }else{
                    Picasso.with(getContext()).load(R.drawable.pudge).transform(new CircleTransform()).into(imageViewBubble);
                }
                imageViewBubble.setLayoutParams(params);
                mLayout.addView(imageViewBubble);
            }
        }
        start();
        Log.d("bubbles", mUsers.length+"");
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, final MotionEvent event) {
                for(int i = 0; i < mLayout.getChildCount(); i++){
                    AbsoluteLayout.LayoutParams paramsBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mStartClickTime = Calendar.getInstance().getTimeInMillis();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            update(i, paramsBubble, event);
                            break;
                        case MotionEvent.ACTION_UP:
                            long clickDuration = Calendar.getInstance().getTimeInMillis() - mStartClickTime;
                            if (clickDuration < MAX_CLICK_DURATION) {
                                isBubble(event);
                            }
                            return false;
                        case MotionEvent.ACTION_CANCEL:

                            break;
                    }
                    mXPrevious[i] = (int) event.getX();
                    mYPrevious[i] = (int) event.getY();
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
                profileFragment.setName(new UserData(1, i+"", "City", "ImageURL"));
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
                mDifferenceX = (int) event.getX() - mXPrevious[i];
                mDifferenceY = (int) event.getY() - mYPrevious[i];

                //MOVING
                paramsBubble.x += mDifferenceX;
                paramsBubble.y += mDifferenceY;
                //MOVING

                //Log.d("differenceX", mDifferenceX + "");
                //Log.d("differenceY", mDifferenceY + "");

                //BORDERING
                AbsoluteLayout.LayoutParams paramsLeftTopBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(0).getLayoutParams();
                if (paramsLeftTopBorderBubble.x > mDefaultBubbleDiameter / 2) {
                    for (int j = 0; j < mLayout.getChildCount(); j++) {
                        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                        params.x -= mDifferenceX;
                        mLayout.getChildAt(j).setLayoutParams(params);
                    }
                }
                if(paramsLeftTopBorderBubble.y > mDefaultBubbleDiameter){
                    for (int j = 0; j < mLayout.getChildCount(); j++) {
                        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                        params.y -= mDifferenceY;
                        mLayout.getChildAt(j).setLayoutParams(params);
                    }
                }
                AbsoluteLayout.LayoutParams paramsRightBottomBorderBubble = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(mLayout.getChildCount()-1).getLayoutParams();
                if(paramsRightBottomBorderBubble.x < (mDisplayCenterX - (mDefaultBubbleDiameter / 2))){
                    for (int j = 0; j < mLayout.getChildCount(); j++) {
                        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                        params.x -= mDifferenceX;
                        mLayout.getChildAt(j).setLayoutParams(params);
                    }
                }
                if(paramsRightBottomBorderBubble.y < mDisplayCenterY){
                    for (int j = 0; j < mLayout.getChildCount(); j++) {
                        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(j).getLayoutParams();
                        params.y -= mDifferenceY;
                        mLayout.getChildAt(j).setLayoutParams(params);
                    }
                }
                //BORDERING

                //SCALING
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
                    //Log.d("xToSide", pixelsToSideFromBubbleX + "");
                    //Log.d("yToSide", pixelsToSideFromBubbleY + "");
                if(pixelsToSideFromBubbleX > 0 && pixelsToSideFromBubbleY > 0) {
                    toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
                    toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
                }else{
                    toSideFromBubblePercentX = 0;
                    toSideFromBubblePercentY = 0;
                }
                if(i == 6) {
                    //Log.d("scaleX", pixelsToSideFromBubbleX + "");
                    //Log.d("scaleY", pixelsToSideFromBubbleY + "");
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
                //SCALING
            }
        }
        mLayout.getChildAt(i).setLayoutParams(paramsBubble);
    }

    private void start(){
        for(int i = 0; i < mLayout.getChildCount(); i++){
            AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mLayout.getChildAt(i).getLayoutParams();
            double pixelsToSideFromBubbleX = 0;
            double toSideFromBubblePercentX = 0;
            double pixelsToSideFromBubbleY = 0;
            double toSideFromBubblePercentY = 0;
            if(params.x < mDisplayCenterX - mDefaultBubbleDiameter / 3){
                pixelsToSideFromBubbleX = params.x + mDefaultBubbleDiameter / 3;
            }else{
                pixelsToSideFromBubbleX = mDisplayCenterX * 2 - params.x - mDefaultBubbleDiameter / 2;
            }
            if(params.y < mDisplayCenterY - 2 * (mDefaultBubbleDiameter / 3)){
                pixelsToSideFromBubbleY = params.y;
            }else{
                pixelsToSideFromBubbleY = mDisplayCenterY * 2 - params.y - mDefaultBubbleDiameter / 3;
            }
            toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
            toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
            if(toSideFromBubblePercentX > toSideFromBubblePercentY){
                if(toSideFromBubblePercentY < 1){
                    params.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleDiameter);
                }else{
                    if(toSideFromBubblePercentY < 0){
                        params.height = 0;
                    }else {
                        params.height = mDefaultBubbleDiameter;

                    }
                }
            }else {
                if(toSideFromBubblePercentX < 1){
                    params.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleDiameter);
                }else{
                    if(toSideFromBubblePercentX < 0){
                        params.height = 0;
                    }else {
                        params.height = mDefaultBubbleDiameter;
                    }
                }
            }
            mLayout.getChildAt(i).setLayoutParams(params);
        }
    }

}

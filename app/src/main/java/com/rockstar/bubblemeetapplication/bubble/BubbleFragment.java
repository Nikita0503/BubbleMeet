package com.rockstar.bubblemeetapplication.bubble;

import android.graphics.Point;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.profile.ProfileFragment;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class BubbleFragment extends Fragment {

    //private int x=0;
    //private int y=0;
    //private int x1 = 0;
    //private int y1 = 0;
    //private int[] mXView;
    //private int[] mYView;
//
    //private static final int MAX_CLICK_DURATION = 100;
    //private long mStartClickTime;
    //private int mDisplayCenterX;
    //private int mDisplayCenterY;
    //private int[] mXPrevious;
    //private int[] mYPrevious;
    //private int mDefaultBubbleWidth;
    //private int mDefaultBubbleHeight;
    //private int mPixelsToSide100percentX;
    //private int mPixelsToSide100percentY;
//
    //private ImageView imageView;
    //private ImageView imageView2;
    //private ImageView imageView3;
    //private ImageView imageView4;
    //private ImageView imageView5;
    //private ImageView imageView6;
    //private ImageView imageView7;
    //private ImageView imageView8;
    //private ImageView imageView9;
    //private ImageView imageView10;
    //private ImageView imageView11;
    //private ImageView imageView12;
    //private ImageView imageView13;
    //private ImageView imageView14;
    //private ImageView imageView15;
    //private ImageView imageView16;
    //private ImageView imageView17;
    //private ImageView imageView18;
    //private ImageView imageView19;
    //private ImageView imageView20;
    //private ImageView imageView21;
    //private ImageView imageView22;
    //private ImageView imageView23;
    //private ImageView imageView24;
//
//
    //private AbsoluteLayout mLayout;
//
    //private ImageView[] mImageViews;
//
//
//
    //@Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                         Bundle savedInstanceState) {
//
    //    return inflater.inflate(R.layout.fragment_bubble_grid_view, null);
    //}
//
    //@Override
    //public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    //    view.setOnTouchListener(new View.OnTouchListener() {
    //        @Override
    //        public boolean onTouch(View view, MotionEvent motionEvent) {
    //            Log.d("TAG123", view.getId()+"");
    //            return true;
    //        }
    //    });
    //    imageView = (ImageView) view.findViewById(R.id.imageView1);
    //    imageView2 = (ImageView) view.findViewById(R.id.imageView2);
    //    imageView3 = (ImageView) view.findViewById(R.id.imageView3);
    //    imageView4 = (ImageView) view.findViewById(R.id.imageViewBubbles);
    //    imageView5 = (ImageView) view.findViewById(R.id.imageView5);
    //    imageView6 = (ImageView) view.findViewById(R.id.imageView6);
    //    imageView7 = (ImageView) view.findViewById(R.id.imageView7);
    //    imageView8 = (ImageView) view.findViewById(R.id.imageViewMessage);
    //    imageView9 = (ImageView) view.findViewById(R.id.imageView9);
    //    imageView10 = (ImageView) view.findViewById(R.id.imageView10);
    //    imageView11 = (ImageView) view.findViewById(R.id.imageView11);
    //    imageView12 = (ImageView) view.findViewById(R.id.imageView12);
    //    imageView13 = (ImageView) view.findViewById(R.id.imageView13);
    //    imageView14 = (ImageView) view.findViewById(R.id.imageView14);
    //    imageView15 = (ImageView) view.findViewById(R.id.imageView15);
    //    imageView16 = (ImageView) view.findViewById(R.id.imageView16);
    //    imageView17 = (ImageView) view.findViewById(R.id.imageView17);
    //    imageView18 = (ImageView) view.findViewById(R.id.imageView18);
    //    imageView19 = (ImageView) view.findViewById(R.id.imageView19);
    //    imageView20 = (ImageView) view.findViewById(R.id.imageView20);
    //    imageView21 = (ImageView) view.findViewById(R.id.imageView21);
    //    imageView22 = (ImageView) view.findViewById(R.id.imageView22);
    //    imageView23 = (ImageView) view.findViewById(R.id.imageView23);
    //    imageView24 = (ImageView) view.findViewById(R.id.imageView24);
//
    //    mImageViews = new ImageView[]{imageView, imageView2, imageView3,
    //            imageView4, imageView5, imageView6, imageView7, imageView8,
    //            imageView9, imageView10, imageView11, imageView12, imageView13,
    //            imageView14, imageView15, imageView16, imageView17, imageView18,
    //            imageView19, imageView20, imageView21, imageView22, imageView23,
    //            imageView24};
//
    //    mXPrevious = new int[mImageViews.length];
    //    mYPrevious = new int[mImageViews.length];
    //    mXView = new int[mImageViews.length];
    //    mYView = new int[mImageViews.length];
    //    mLayout = (AbsoluteLayout) view.findViewById(R.id.layout);
    //    AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
    //    mDefaultBubbleWidth = params.width;
    //    mDefaultBubbleHeight = params.height;
    //    Display display = getActivity().getWindowManager().getDefaultDisplay();
    //    Point size = new Point();
    //    display.getSize(size);
    //    mDisplayCenterX = size.x / 2;
    //    mDisplayCenterY = size.y / 2 - size.y / 10;
    //    //mDisplayCenterXWithoutRadius = mDisplayCenterX - mDefaultBubbleWidth / 2;
    //    //mDisplayCenterYWithoutRadius = mDisplayCenterY - mDefaultBubbleHeight / 2;
    //    mPixelsToSide100percentX = mDisplayCenterX - mDisplayCenterX / 4;
    //    mPixelsToSide100percentY = mDisplayCenterY - mDisplayCenterY / 4;
    //    Log.d("HeightAndWidth", "h = " + mDisplayCenterY + ", w = " + mDisplayCenterX);
    //    initViews();
    //}
//
    //@Override
    //public void initViews() {
    //    ((MainActivity) getActivity()).hideButtonBack();
    //    //mImageViews[0].setOnClickListener(new View.OnClickListener() {
    //    //    @Override
    //    //    public void onClick(View view) {
    //    //        FragmentTransaction transaction = getFragmentManager().beginTransaction();
    //    //        ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
    //    //        profileFragment.setName(new UserData(1 , "Name", "City", "ImageURL"));
    //    //        transaction.replace(R.id.root_fragment, profileFragment);
    //    //        transaction.addToBackStack(null);
    //    //        transaction.commit();
    //    //    }
    //    //});
    //    setStartPositions();
//
    //    mLayout.setOnTouchListener(new View.OnTouchListener() {
    //        @Override
    //        public boolean onTouch(View view, MotionEvent event) {
    //            for (int i = 0; i < mImageViews.length; i++) {
    //                AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mImageViews[i].getLayoutParams();
    //                switch (event.getAction()) {
    //                    case MotionEvent.ACTION_DOWN:
    //                        break;
    //                    case MotionEvent.ACTION_MOVE:
    //                        if (mXPrevious[i] > 0 && mXPrevious[i] < (mDisplayCenterX * 2)) {
    //                            int width = 0;
    //                            int height = 0;
    //                            if (mXPrevious[i] != 0 && mYPrevious[i] != 0) {
    //                                int differenceX = (int) event.getX() - mXPrevious[i];
    //                                int differenceY = (int) event.getY() - mYPrevious[i];
//
    //                                AbsoluteLayout.LayoutParams params1 = (AbsoluteLayout.LayoutParams) mImageViews[1].getLayoutParams();
    //                                AbsoluteLayout.LayoutParams params4 = (AbsoluteLayout.LayoutParams) mImageViews[4].getLayoutParams();
    //                                AbsoluteLayout.LayoutParams params20 = (AbsoluteLayout.LayoutParams) mImageViews[20].getLayoutParams();
    //                                AbsoluteLayout.LayoutParams params14 = (AbsoluteLayout.LayoutParams) mImageViews[13].getLayoutParams();
//
    //                                if(params4.x > 180){
    //                                    for (int j = 0; j < mImageViews.length; j++) {
//
    //                                        AbsoluteLayout.LayoutParams params2 = (AbsoluteLayout.LayoutParams) mImageViews[j].getLayoutParams();
    //                                        params2.x -= differenceX;
    //                                        mImageViews[j].setLayoutParams(params2);
    //                                    }
    //                                }
    //                                if(params1.y > 360){
    //                                    for (int j = 0; j < mImageViews.length; j++) {
//
    //                                        AbsoluteLayout.LayoutParams params2 = (AbsoluteLayout.LayoutParams) mImageViews[j].getLayoutParams();
    //                                        params2.y -= differenceY;
    //                                        mImageViews[j].setLayoutParams(params2);
    //                                    }
    //                                }
    //                                if(params20.y < 980){
    //                                    for (int j = 0; j < mImageViews.length; j++) {
    //                                        AbsoluteLayout.LayoutParams params2 = (AbsoluteLayout.LayoutParams) mImageViews[j].getLayoutParams();
    //                                        params2.y -= differenceY;
    //                                        mImageViews[j].setLayoutParams(params2);
    //                                    }
    //                                }
    //                                if(params14.x < 600){
    //                                    for (int j = 0; j < mImageViews.length; j++) {
    //                                        AbsoluteLayout.LayoutParams params2 = (AbsoluteLayout.LayoutParams) mImageViews[j].getLayoutParams();
    //                                        params2.x -= differenceX;
    //                                        mImageViews[j].setLayoutParams(params2);
    //                                    }
    //                                }
    //                                params.x = params.x + differenceX;
    //                                params.y = params.y + differenceY;
//
    //                                double pixelsToSideFromBubbleX = 0;
    //                                double toSideFromBubblePercentX = 0;
    //                                double pixelsToSideFromBubbleY = 0;
    //                                double toSideFromBubblePercentY = 0;
    //                                if(params.x < mDisplayCenterX - mDefaultBubbleWidth / 3){
    //                                    pixelsToSideFromBubbleX = params.x + mDefaultBubbleWidth / 3;
    //                                }else{
    //                                    pixelsToSideFromBubbleX = mDisplayCenterX * 2 - params.x - mDefaultBubbleWidth / 2;
    //                                }
    //                                if(params.y < mDisplayCenterY - 2 * (mDefaultBubbleHeight / 3)){
    //                                    pixelsToSideFromBubbleY = params.y;
    //                                }else{
    //                                    pixelsToSideFromBubbleY = mDisplayCenterY * 2 - params.y - mDefaultBubbleHeight / 3;
    //                                }
    //                                if(i == 4) {
    //                                    Log.d("TAG12", pixelsToSideFromBubbleX + "");
    //                                }
    //                                toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
    //                                toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
    //                                if(toSideFromBubblePercentX > toSideFromBubblePercentY){
    //                                    if(toSideFromBubblePercentY < 1){
    //                                        params.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleHeight);
    //                                    }else{
    //                                        if(toSideFromBubblePercentY < 0){
    //                                            params.height = 0;
    //                                        }else {
    //                                            params.height = mDefaultBubbleHeight;
//
    //                                        }
    //                                    }
    //                                }else {
    //                                    if(toSideFromBubblePercentX < 1){
    //                                        params.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleHeight);
    //                                    }else{
    //                                        if(toSideFromBubblePercentX < 0){
    //                                            params.height = 0;
    //                                        }else {
    //                                            params.height = mDefaultBubbleHeight;
    //                                        }
    //                                    }
    //                                }
//
    //                            }
    //                        }
    //                        mImageViews[i].setLayoutParams(params);
    //                        break;
    //                    case MotionEvent.ACTION_UP:
    //                        break;
    //                    case MotionEvent.ACTION_CANCEL:
    //                        break;
    //                }
    //                mXPrevious[i] = (int) event.getX();
    //                mYPrevious[i] = (int) event.getY();
    //                AbsoluteLayout.LayoutParams params2 = (AbsoluteLayout.LayoutParams) mImageViews[4].getLayoutParams();
//
    //            }
    //            return true;
//
    //        }
    //    });
//
    //    for(int i = 0; i < mImageViews.length; i++){
    //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mImageViews[i].getLayoutParams();
    //        mXView[i] = params.x;
    //        mYView[i] = params.y;
    //    }
    //    for(int i = 0; i < mImageViews.length; i++) {
    //        mImageViews[i].setOnTouchListener(new View.OnTouchListener() {
    //            @Override
    //            public boolean onTouch(View view, MotionEvent event) {
    //                for(int j = 0; j < mImageViews.length; j++) {
    //                    switch (event.getAction()) {
    //                        case MotionEvent.ACTION_MOVE:
//
    //                            break;
    //                        case MotionEvent.ACTION_DOWN:
    //                            mStartClickTime = Calendar.getInstance().getTimeInMillis();
    //                            x1 = (int) event.getX();
    //                            y1 = (int) event.getY();
    //                            x = (int) event.getX();
    //                            y = (int) event.getY();
    //                            break;
    //                        case MotionEvent.ACTION_UP:
    //                            long clickDuration = Calendar.getInstance().getTimeInMillis() - mStartClickTime;
    //                            //Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
    //                            if (clickDuration < MAX_CLICK_DURATION) {
    //                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
    //                                ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
    //                                profileFragment.setName(new UserData(1, "Name", "City", "ImageURL"));
    //                                transaction.replace(R.id.root_fragment, profileFragment);
    //                                transaction.addToBackStack(null);
    //                                transaction.commit();
    //                            }
    //                            break;
//
    //                    }
    //                }
    //                return true;
//
    //            }
    //        });
    //    }
    //}
//
    //private void setStartPositions(){
    //    for(int i = 0; i < mImageViews.length; i++){
    //        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mImageViews[i].getLayoutParams();
    //        int differenceX = (int) mImageViews[i].getX() - mDisplayCenterX;
    //        int differenceY = (int) mImageViews[i].getY() - mDisplayCenterY;
    //        double pixelsToSideFromBubbleX = 0;
    //        double toSideFromBubblePercentX = 0;
    //        double pixelsToSideFromBubbleY = 0;
    //        double toSideFromBubblePercentY = 0;
    //        if(params.x < mDisplayCenterX - mDefaultBubbleWidth / 3){
    //            pixelsToSideFromBubbleX = params.x + mDefaultBubbleWidth / 3;
    //        }else{
    //            pixelsToSideFromBubbleX = mDisplayCenterX * 2 - params.x - mDefaultBubbleWidth / 2;
    //        }
    //        if(params.y < mDisplayCenterY - 2 * (mDefaultBubbleHeight / 3)){
    //            pixelsToSideFromBubbleY = params.y;
    //        }else{
    //            pixelsToSideFromBubbleY = mDisplayCenterY * 2 - params.y - mDefaultBubbleHeight / 3;
    //        }
    //        toSideFromBubblePercentX = pixelsToSideFromBubbleX / (mDisplayCenterX - mDisplayCenterX / 2);
    //        toSideFromBubblePercentY = pixelsToSideFromBubbleY / (mDisplayCenterY - mDisplayCenterY / 2);
    //        if(toSideFromBubblePercentX > toSideFromBubblePercentY){
    //            if(toSideFromBubblePercentY < 1){
    //                params.height = (int) (Math.abs(toSideFromBubblePercentY) * mDefaultBubbleHeight);
    //            }else{
    //                if(toSideFromBubblePercentY < 0){
    //                    params.height = 0;
    //                }else {
    //                    params.height = mDefaultBubbleHeight;
//
    //                }
    //            }
    //        }else {
    //            if(toSideFromBubblePercentX < 1){
    //                params.height = (int) (Math.abs(toSideFromBubblePercentX) * mDefaultBubbleHeight);
    //            }else{
    //                if(toSideFromBubblePercentX < 0){
    //                    params.height = 0;
    //                }else {
    //                    params.height = mDefaultBubbleHeight;
    //                }
    //            }
    //        }
    //        mImageViews[i].setLayoutParams(params);
    //    }
    //}
//
    //@Override
    //public void showMessage(String message) {
    //    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    //}
}
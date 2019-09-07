package com.rockstar.bubblemeetapplication.bubble;

import android.app.ActionBar;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rockstar.bubblemeetapplication.BaseContract;
import com.rockstar.bubblemeetapplication.DataAdapter;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.main.MainActivity;

public class BubbleFragment extends Fragment implements BaseContract.BaseView {

    int mDisplayCenterX;
    int mDisplayCenterY;
    int mDisplayCenterXWithoutRadius;
    int mDisplayCenterYWithoutRadius;
    int[] mXPrevious;
    int[] mYPrevious;
    int mDefaultBubbleWidth;
    int mDefaultBubbleHeight;
    int mPixelsToSide100percentX;
    int mPixelsToSide100percentY;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;

    AbsoluteLayout layout;

    ImageView[] mImageViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bubble_grid_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView = (ImageView) view.findViewById(R.id.imageView1);
        //imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        //imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        //imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        //imageView5 = (ImageView) view.findViewById(R.id.imageView5);
        //imageView6 = (ImageView) view.findViewById(R.id.imageView6);
        //imageView7 = (ImageView) view.findViewById(R.id.imageView7);
        //imageView8 = (ImageView) view.findViewById(R.id.imageView8);
        //imageView9 = (ImageView) view.findViewById(R.id.imageView9);
        //imageView10 = (ImageView) view.findViewById(R.id.imageView10);
        //imageView11 = (ImageView) view.findViewById(R.id.imageView11);
        mImageViews = new ImageView[]{imageView/*, imageView2, imageView3,
                imageView4, imageView5, imageView6, imageView7, imageView8,
                imageView9, imageView10, imageView11*/};
        mXPrevious = new int[mImageViews.length];
        mYPrevious = new int[mImageViews.length];
        layout = (AbsoluteLayout) view.findViewById(R.id.layout);
        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
        mDefaultBubbleWidth = params.width;
        mDefaultBubbleHeight = params.height;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mDisplayCenterX = size.x / 2;
        mDisplayCenterY = size.y / 2 - size.y/10;
        //mDisplayCenterXWithoutRadius = mDisplayCenterX - mDefaultBubbleWidth / 2;
        //mDisplayCenterYWithoutRadius = mDisplayCenterY - mDefaultBubbleHeight / 2;
        mPixelsToSide100percentX = mDisplayCenterX - mDisplayCenterX/4;
        mPixelsToSide100percentY = mDisplayCenterY - mDisplayCenterY/4;
        Log.d("HeightAndWidth", "h = " + mDisplayCenterY+ ", w = " + mDisplayCenterX);
        initViews();
    }

    @Override
    public void initViews() {
        ((MainActivity) getActivity()).hideButtonBack();
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                for(int i = 0; i < mImageViews.length; i++){
                AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) mImageViews[i].getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(mXPrevious[i] > 0 && mXPrevious[i] < (mDisplayCenterX * 2)) {
                            if (mXPrevious[i] != 0 && mYPrevious[i] != 0) {
                                //Log.d("LOG", "xPrevious = " + mXPrevious);
                                //Log.d("LOG", "x = " + event.getX());
                                //Log.d("LOG", "yPrevious = " + mYPrevious);
                                //Log.d("LOG", "y = " + event.getY());

                                int differenceX = (int) event.getX() - mXPrevious[i];
                                int differenceY = (int) event.getY() - mYPrevious[i];
                                params.x = params.x + differenceX;
                                params.y = params.y + differenceY;
                                double pixelsToSideFromBubbleX;
                                double toSideFromBubblePercentX;
                                if(params.x < mDisplayCenterX - mDefaultBubbleWidth / 2){
                                    pixelsToSideFromBubbleX = params.x + mDefaultBubbleWidth / 2;
                                }else{
                                    pixelsToSideFromBubbleX = mDisplayCenterX * 2 - params.x - mDefaultBubbleWidth / 2;
                                }
                                //Log.d("ToSide", pixelsToSideFromBubbleX + " pixels to side");
                                toSideFromBubblePercentX = pixelsToSideFromBubbleX  / mDisplayCenterX;
                                if(toSideFromBubblePercentX > 0){
                                    params.height = (int) (mDefaultBubbleHeight * toSideFromBubblePercentX);
                                }
                                Log.d("percent", toSideFromBubblePercentX + "%");
                                //int differenceX = (int) event.getX() - mXPrevious[i];
                                //int differenceY = (int) event.getY() - mYPrevious[i];
                                //params.x = params.x + differenceX;
                                //params.y = params.y + differenceY;
                                //double pixelsToSideFromBubbleX;
                                //double pixelsToSideFromBubbleY;
                                //double toSideFromBubblePercentX;
                                //double toSideFromBubblePercentY;
                                //if (params.x < mDisplayCenterX) {
                                //    pixelsToSideFromBubbleX = params.x;
                                //} else {
                                //    pixelsToSideFromBubbleX = Math.abs(params.x - mDisplayCenterX * 2);
                                //}
                                //if (params.y < mDisplayCenterY) {
                                //    pixelsToSideFromBubbleY = params.y - mDefaultBubbleWidth;
                                //} else {
                                //    pixelsToSideFromBubbleY = Math.abs(params.y - mDisplayCenterY * 2);
                                //}
                                //toSideFromBubblePercentX = pixelsToSideFromBubbleX / mPixelsToSide100percentX;
                                //toSideFromBubblePercentY = pixelsToSideFromBubbleY / mPixelsToSide100percentY;
                                //if(params.x > 0 && params.x < mDisplayCenterX * 2 && params.y > 0 && params.y < mDisplayCenterY * 2) {
                                //    params.height = (int) (mDefaultBubbleHeight * toSideFromBubblePercentY);
                                //    params.width = (int) (mDefaultBubbleWidth * toSideFromBubblePercentX);
                                //}
                                //Log.d("ToSide", pixelsToSideFromBubbleX + "");

                            }
                        }
                        mImageViews[i].setLayoutParams(params);
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
    }

}
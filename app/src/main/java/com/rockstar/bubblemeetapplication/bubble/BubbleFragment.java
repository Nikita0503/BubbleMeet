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
    int mXPrevious;
    int mYPrevious;
    int mDefaultBubbleWidth;
    int mDefaultBubbleHeight;


    ImageView imageView;
    AbsoluteLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mXPrevious = 0;
        mYPrevious = 0;
        return inflater.inflate(R.layout.fragment_bubble_grid_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView = (ImageView) view.findViewById(R.id.imageView5);
        layout = (AbsoluteLayout) view.findViewById(R.id.layout);
        AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
        mDefaultBubbleWidth = params.width;
        mDefaultBubbleHeight = params.height;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mDisplayCenterX = size.x / 2;
        mDisplayCenterY = size.y / 2;
        mDisplayCenterXWithoutRadius = mDisplayCenterX - mDefaultBubbleWidth / 2;
        mDisplayCenterYWithoutRadius = mDisplayCenterY - mDefaultBubbleHeight / 2;
        Log.d("percent", "h = " + mDisplayCenterY+ ", w = " + mDisplayCenterX);
        initViews();
    }

    @Override
    public void initViews() {
        ((MainActivity) getActivity()).hideButtonBack();
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(mXPrevious != 0 && mYPrevious != 0) {
                            //Log.d("LOG", "xPrevious = " + mXPrevious);
                            //Log.d("LOG", "x = " + event.getX());
                            //Log.d("LOG", "yPrevious = " + mYPrevious);
                            //Log.d("LOG", "y = " + event.getY());
                            int differenceX = (int) event.getX() - mXPrevious;
                            int differenceY = (int) event.getY() - mYPrevious;
                            params.x = params.x - differenceX;
                            params.y = params.y - differenceY;
                            if(params.x < (mDisplayCenterX + mDisplayCenterX/3) && params.x > (mDisplayCenterX - mDisplayCenterX/3)) {
                                params.height = mDefaultBubbleHeight;
                                params.width = mDefaultBubbleWidth;
                            }else{
                                params.height = mDefaultBubbleHeight / 2;
                                params.width = mDefaultBubbleWidth / 2;
                            }

                            imageView.setLayoutParams(params);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                mXPrevious = (int)event.getX();
                mYPrevious = (int)event.getY();
                return true;
            }
        });
    }

}
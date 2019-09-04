package com.rockstar.bubblemeetapplication.bubble;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
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

public class BubbleFragment extends Fragment implements BaseContract.BaseView {

    int xPrevious;
    int yPrevious;
    String sDown;
    String sMove;
    String sUp;

    ImageView imageView;
    AbsoluteLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        xPrevious = 0;
        yPrevious = 0;
        return inflater.inflate(R.layout.fragment_bubble_grid_view, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView = (ImageView) view.findViewById(R.id.imageView5);
        layout = (AbsoluteLayout) view.findViewById(R.id.layout);
        initViews();
    }

    @Override
    public void initViews() {
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) imageView.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        if(xPrevious != 0 && yPrevious != 0) {
                            Log.d("LOG", "xPrevious = " + xPrevious);
                            Log.d("LOG", "x = " + event.getX());
                            Log.d("LOG", "yPrevious = " + yPrevious);
                            Log.d("LOG", "y = " + event.getY());

                            int differenceX = (int) event.getX() - xPrevious;
                            int differenceY = (int) event.getY() - yPrevious;

                            params.x = params.x - differenceX;
                            params.y = params.y - differenceY;
                            imageView.setLayoutParams(params);
                        }
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                xPrevious = (int)event.getX();
                yPrevious = (int)event.getY();
                return true;
            }
        });
    }


}
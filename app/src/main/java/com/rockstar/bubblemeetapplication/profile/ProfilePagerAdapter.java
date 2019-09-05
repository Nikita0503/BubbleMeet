package com.rockstar.bubblemeetapplication.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rockstar.bubblemeetapplication.R;

public class ProfilePagerAdapter extends PagerAdapter {

    Context context;
    private int[] mImages = new int[] {
            R.drawable.image4,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };
    ProfilePagerAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);

        imageView.setImageResource(mImages[position]);
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
package com.rockstar.bubblemeetapplication.profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.squareup.picasso.Picasso;

public class ProfilePagerAdapter extends PagerAdapter {

    private UserData mUser;
    private Context mContext;
    private int[] mImages;

    ProfilePagerAdapter(Context context, UserData user){
        mContext=context;
        mUser = user;
        mImages = new int[] {
                R.drawable.bmw,
                R.drawable.pudge
        };

    }


    @Override
    public int getCount() {
        return mImages.length+1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView;
        if(position == 0) {
            imageView = new ImageView(mContext);
            Picasso.with(mContext)
                    .load(mUser.getPhoto())
                    .into(imageView);
        }else{
            imageView = new ImageView(mContext);
            imageView.setImageResource(mImages[position-1]);
        }
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
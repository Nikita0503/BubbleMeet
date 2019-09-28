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
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.squareup.picasso.Picasso;

public class ProfilePagerAdapter extends PagerAdapter {

    private UserDataFull mUser;
    private Context mContext;

    ProfilePagerAdapter(Context context, UserDataFull user){
        mContext=context;
        mUser = user;
    }


    @Override
    public int getCount() {
        if(mUser.photo == null){
            return 1;
        }else {
            return mUser.photo.size() + 1;
        }
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
                    .load("http://185.25.116.211:11000/image/" + mUser.avatarFull)
                    .into(imageView);
        }else{
            imageView = new ImageView(mContext);
            Picasso.with(mContext)
                    .load("http://185.25.116.211:11000/image/" + mUser.photo.get(position-1))
                    .into(imageView);
        }
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
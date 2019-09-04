package com.rockstar.bubblemeetapplication.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;


public class BubbleAdapter extends BaseAdapter {

    String[] mNames;
    Context mContext;
    FragmentManager mFragmentManager;

    public BubbleAdapter(Context c, String[] names, FragmentManager fragmentManager) {
        mContext = c;
        mNames = names;
        mFragmentManager = fragmentManager;
    }

    public int getCount() {
        return mNames.length;
    }

    public Object getItem(int position) {
        return mNames[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        if (convertView == null) {
            grid = new View(mContext);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cell_bubble, parent, false);
        } else {
            grid = (View) convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(R.id.imageViewBubble);
        //imageView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //        ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
        //        profileFragment.setName(mNames[position]);
        //        transaction.replace(R.id.root_fragment, profileFragment);
        //        transaction.addToBackStack(null);
        //        transaction.commit();
        //    }
        //});
        imageView.setImageResource(R.drawable.circle_for_photo_gray);
        return grid;
    }


}
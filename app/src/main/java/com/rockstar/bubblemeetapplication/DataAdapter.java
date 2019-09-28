package com.rockstar.bubblemeetapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.abdularis.civ.CircleImageView;
import com.rockstar.bubblemeetapplication.main.MainActivity;
import com.rockstar.bubblemeetapplication.model.data.UserData;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.rockstar.bubblemeetapplication.profile_preview.ProfilePreviewFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DataAdapter extends BaseAdapter {

    ArrayList<UserDataFull> mUsers;
    FragmentManager mFragmentManager;
    MainActivity mActivity;

    public DataAdapter(MainActivity activity, FragmentManager fragmentManager, ArrayList<UserDataFull> users) {
        mActivity = activity;
        mFragmentManager = fragmentManager;
        mUsers = users;
    }

    public int getCount() {
        return mUsers.size();
    }

    public Object getItem(int position) {
        return mUsers.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        if (convertView == null) {
            grid = new View(mActivity.getApplicationContext());
            LayoutInflater inflater = (LayoutInflater) mActivity.getApplicationContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.cell_grid, parent, false);
        } else {
            grid = (View) convertView;
        }
        TextView textViewName = (TextView) grid.findViewById(R.id.textViewName);
        textViewName.setTextColor(mActivity.getResources().getColor(R.color.colorName2));
        textViewName.setText(mUsers.get(position).name);
        TextView textViewCity = (TextView) grid.findViewById(R.id.textViewCity);
        textViewCity.setTextColor(mActivity.getResources().getColor(R.color.colorName2));
        textViewCity.setText(mUsers.get(position).city);
        CircleImageView imageView = (CircleImageView) grid.findViewById(R.id.imageViewAvatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
                profileFragment.setUser(position, mUsers);
                transaction.replace(R.id.root_fragment, profileFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        Picasso.with(mActivity.getApplicationContext())
                .load(mUsers.get(position).avatarFull)
                .into(imageView);

        //imageView.setImageResource(com.rockstar.bubblemeetapplication.R.drawable.circle_for_photo_gray);

        return grid;
    }

    public void addUsers(ArrayList<UserDataFull> users){
        mUsers.addAll(users);
        notifyDataSetChanged();
    }
}
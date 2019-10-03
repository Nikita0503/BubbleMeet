package com.rockstar.bubblemeetapplication.profile_preview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.CircleTransform;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfilePreviewCustomAdapter extends RecyclerView.Adapter<ProfilePreviewCustomAdapter.ViewHolder> {

    private ArrayList<UserDataFull> mAllUsers;
    private ArrayList<UserDataFull> mUsers;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public ProfilePreviewCustomAdapter(FragmentManager fragmentManager, Context context, ArrayList<UserDataFull> allUsers){
        mUsers = new ArrayList<UserDataFull>();
        mAllUsers = allUsers;
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cell_only_avatar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load("http://185.25.116.211:11000/image/" + mUsers.get(position).avatarSmall)
                .transform(new CircleTransform(0))
                .into(holder.imageViewAvatar);
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                ProfilePreviewFragment profileFragment = new ProfilePreviewFragment();
                for(int i = 0; i < mAllUsers.size(); i++) {
                    if(mAllUsers.get(i).id == mUsers.get(position).id) {
                        profileFragment.setUser(i, mAllUsers);
                        transaction.replace(R.id.root_fragment, profileFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setTemporaryFavourite(ArrayList<UserDataFull> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAvatar;

        public ViewHolder(View itemView){
            super(itemView);
            imageViewAvatar = (ImageView) itemView.findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewAvatar);
        }
    }
}

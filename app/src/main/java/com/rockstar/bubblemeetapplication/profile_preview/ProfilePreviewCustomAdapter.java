package com.rockstar.bubblemeetapplication.profile_preview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.CircleTransform;
import com.rockstar.bubblemeetapplication.R;
import com.rockstar.bubblemeetapplication.model.data.UserDataFull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfilePreviewCustomAdapter extends RecyclerView.Adapter<ProfilePreviewCustomAdapter.ViewHolder> {

    ArrayList<UserDataFull> mUsers;
    Context mContext;

    public ProfilePreviewCustomAdapter(Context context){
        mUsers = new ArrayList<UserDataFull>();
        mContext = context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(mContext)
                .load("http://185.25.116.211:11000/image/" + mUsers.get(position).avatarSmall)
                .transform(new CircleTransform())
                .into(holder.imageViewAvatar);
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

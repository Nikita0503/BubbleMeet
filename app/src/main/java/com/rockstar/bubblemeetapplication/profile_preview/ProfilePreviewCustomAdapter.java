package com.rockstar.bubblemeetapplication.profile_preview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rockstar.bubblemeetapplication.R;

public class ProfilePreviewCustomAdapter extends RecyclerView.Adapter<ProfilePreviewCustomAdapter.ViewHolder> {

    String[] mNames;
    Context mContext;

    public ProfilePreviewCustomAdapter(Context context){
        mNames = new String[]{"Some", "Names", "Contains", "This", "List"};
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.cell_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewName.setText(mNames[position]);
        holder.imageViewAvatar.setImageDrawable(mContext.getResources().getDrawable(R.drawable.circle_for_photo_gray));
    }

    @Override
    public int getItemCount() {
        return mNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        ImageView imageViewAvatar;

        public ViewHolder(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(com.rockstar.bubblemeetapplication.R.id.textViewName);
            imageViewAvatar = (ImageView) itemView.findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewAvatar);
        }
    }
}

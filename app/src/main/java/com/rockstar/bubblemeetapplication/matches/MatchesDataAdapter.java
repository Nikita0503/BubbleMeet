package com.rockstar.bubblemeetapplication.matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MatchesDataAdapter extends BaseAdapter {
    private Context mContext;

    String[] names;

    public MatchesDataAdapter(Context c) {
        mContext = c;
        names = new String[]{"Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben", "Google", "Pudge", "Gaben"};
    }

    public int getCount() {
        return names.length;
    }

    public Object getItem(int position) {
        return names[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        if (convertView == null) {
            grid = new View(mContext);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(com.rockstar.bubblemeetapplication.R.layout.cell_grid, parent, false);
        } else {
            grid = (View) convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(com.rockstar.bubblemeetapplication.R.id.imageViewAvatar);
        TextView textView = (TextView) grid.findViewById(com.rockstar.bubblemeetapplication.R.id.textViewName);
        imageView.setImageResource(com.rockstar.bubblemeetapplication.R.drawable.circle_for_photo_gray);
        textView.setText(names[position]);
        return grid;
    }


}
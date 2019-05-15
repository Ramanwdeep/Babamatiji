package com.manishpreet.babamatiji.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.manishpreet.babamatiji.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    Context context;
    int [] history_images = new int[] {R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,

    };


    public GridViewAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return history_images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.history_grid_single_item, parent, false);

        ImageView image = view.findViewById(R.id.iv_image);
        image.setImageResource(history_images[position]);
        return view;
    }
}

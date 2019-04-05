package com.manishpreet.babamatiji.createpost;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.manishpreet.babamatiji.R;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.Holder> {

    Context context;
     ArrayList<Uri> selectedImages;

    public GridViewAdapter(Context context) {
        this.context = context;
        selectedImages = new ArrayList<>();
    }


    @NonNull
    @Override
    public GridViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.gridview_single_item, viewGroup, false);

        return new Holder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewAdapter.Holder holder, int i) {

        Glide.with(context)
                .load(selectedImages.get(i))
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return selectedImages.size();
    }

    public void add(Uri data) {
        selectedImages.add(data);
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.grid_iv);
        }
    }
}

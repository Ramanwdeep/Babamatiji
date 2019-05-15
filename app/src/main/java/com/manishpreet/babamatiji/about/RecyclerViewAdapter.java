package com.manishpreet.babamatiji.about;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.manishpreet.babamatiji.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

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

  public RecyclerViewAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View view = LayoutInflater
        .from(context).inflate(R.layout.history_grid_single_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
      viewHolder.image.setImageResource(history_images[i]);

      final int img = history_images[i];




  }

  @Override
  public int getItemCount() {
    return history_images.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
      ImageView image;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      image = itemView.findViewById(R.id.iv_image);

    }
  }
}

package com.manishpreet.babamatiji.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manishpreet.babamatiji.Post;
import com.manishpreet.babamatiji.R;

import java.util.ArrayList;

class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    ArrayList<Post> posts;

    public PostAdapter(HomeFragment homeFragment) {
        posts = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.post_imgage.setImageBitmap(getImage(posts.get(i).getImage()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void add(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView post_imgage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            post_imgage = itemView.findViewById(R.id.post_imgage);
        }
    }


    Bitmap getImage(String imageString) {
        byte[] imageBytes;

        //decode base64 string to image
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        return decodedImage;

    }
}
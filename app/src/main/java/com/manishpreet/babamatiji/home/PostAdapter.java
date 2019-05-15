package com.manishpreet.babamatiji.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.manishpreet.babamatiji.Post;
import com.manishpreet.babamatiji.R;
import com.manishpreet.babamatiji.profile.Likes;
import com.manishpreet.babamatiji.profile.User;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    ArrayList<Post> posts;
    ArrayList<String>ids;
    Context context;

    public PostAdapter(Context context) {
        posts = new ArrayList<>();
        this.context=context;
        ids=new ArrayList();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        if (posts.get(i).getImage().equals("empty")){
            holder.post_imgage.setVisibility(View.GONE);
        }
        else
        {
            holder.post_imgage.setImageBitmap(getImage(posts.get(i).getImage()));
        }
         holder.date.setText(getDate(posts.get(i).getTimestemp()));
        holder.description.setText(posts.get(i).getDesc());



        FirebaseFirestore.getInstance().collection("users")
                .document(posts.get(i).getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            User user=task.getResult().toObject(User.class);
                            holder.name.setText(user.getName());

                            Glide.with(context).load(user.getImage()).into(holder.userImage);

                        }
                    }
                });

            FirebaseFirestore.getInstance().collection("likes").document(ids.get(i)).collection("likes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            holder.txtLike.setText(String.valueOf(task.getResult().size()+" likes"));
                        }
                    }
                });

        holder.likee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Likes like= new Likes(FirebaseAuth.getInstance().getUid(),ids.get(i));
                FirebaseFirestore.getInstance().collection("likes")
                        .document(ids.get(i)).collection("likes").document(FirebaseAuth.getInstance().getUid())
                        .set(like)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    FirebaseFirestore.getInstance().collection("likes").document(ids.get(i))
                                            .collection("likes")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    holder.txtLike.setText(String.valueOf(task.getResult().size()+" likes"));
                                                }
                                            });
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void add(Post post, String id) {
        posts.add(post);
        ids.add(id);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView post_imgage;
        TextView name;
        TextView date;
        TextView txtLike;
        ImageView likee;
        TextView description;
        CircleImageView userImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            post_imgage = itemView.findViewById(R.id.post_imgage);
            likee = itemView.findViewById(R.id.likee);
            name=itemView.findViewById(R.id.name);
            txtLike=itemView.findViewById(R.id.txtlike);
            likee=itemView.findViewById(R.id.likee);
            description=itemView.findViewById(R.id.description);
            date=itemView.findViewById(R.id.date);
            userImage = itemView.findViewById(R.id.img_user_image);
        }
    }


    Bitmap getImage(String imageString) {
        byte[] imageBytes;

        //decode base64 string to image
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        return decodedImage;

    }

    String getDate(Long millisecond)
    {

        // or you already have long value of date, use this instead of milliseconds variable.
        String dateString = DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();

        return dateString;
    }
}
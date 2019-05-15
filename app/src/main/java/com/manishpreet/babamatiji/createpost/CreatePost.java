package com.manishpreet.babamatiji.createpost;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.manishpreet.babamatiji.Post;
import com.manishpreet.babamatiji.Prefrences;
import com.manishpreet.babamatiji.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CreatePost extends AppCompatActivity implements View.OnClickListener {
    
    private RecyclerView recyclerView;
    private Button btnAddPhoto;
    private ImageView done;
    private static final int REQUEST_GALLERY = 101;
    private GridViewAdapter adapter;
    private StorageReference mStorageRef;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog dialog;
    String description;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        firebaseFirestore=FirebaseFirestore.getInstance();
        dialog= new ProgressDialog(this);
        dialog.setMessage("wait a sec...");
        dialog.setCanceledOnTouchOutside(false);
        recyclerView = findViewById(R.id.recyclerView);
        done=findViewById(R.id.done);
        editText=findViewById(R.id.editpostt);
        btnAddPhoto = findViewById(R.id.add_photo);
        adapter = new GridViewAdapter(CreatePost.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        btnAddPhoto.setOnClickListener(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description=editText.getText().toString();
            uploadImage();
                ImageView image =(ImageView)findViewById(R.id.image);
            }
        });
    }

    @Override
    public void onClick(View v) {
        
        switch (v.getId())
        {
            case R.id.add_photo:
            {
                addPhotoFromGallery();
                break;
            }
        }
        
        
    }

    private void addPhotoFromGallery() {
        Intent openGallery = new Intent();
        openGallery.setType("image/*");
        // openGallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        openGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(openGallery,"Select Picture"), REQUEST_GALLERY);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQUEST_GALLERY:
            {
                try {

                    if (data != null)
                    {
                        adapter.add(data.getData());
                        adapter.notifyDataSetChanged();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            }


    }
    void uploadImage(){
        Long timestmp=new Date().getTime();

       if (description.isEmpty())
       {
           Toast.makeText(this, "enter some text", Toast.LENGTH_SHORT).show();
       }else
       {

           dialog.show();
           if (adapter.selectedImages.size()==0)
           {
               saveToDatabase("empty");
           }else
           {
               Uri uri=adapter.selectedImages.get(0);

               Glide.with(this)
                       .asBitmap()
                       .load(uri)
                       .into(new SimpleTarget<Bitmap>() {
                           @Override
                           public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                               getBase64(resource);
                           }
                       });

           }
       }


    }

    private void getBase64(Bitmap bitmap) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        saveToDatabase(imageString);
    }

    private void saveToDatabase(String downloadUrl) {
        Post post=new Post(description,downloadUrl.toString(),"");

        firebaseFirestore.collection("posts").document().set(post)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            dialog.hide();
                            Toast.makeText(CreatePost.this, "post uploded", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.hide();
                Toast.makeText(CreatePost.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

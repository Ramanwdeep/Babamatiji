package com.manishpreet.babamatiji.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.manishpreet.babamatiji.Prefrences;
import com.manishpreet.babamatiji.R;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
 User user;
    private static final int GALLERY_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private Uri capImageURI;
 EditText username;
 EditText address;
 ImageView profile_image;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();    //change the url according to your firebase app

    // EditText pass;
Button save;
FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        firebaseFirestore=FirebaseFirestore.getInstance();
        //user= Prefrences.getUser(this);
        profile_image=findViewById(R.id.profile_image);
        username=findViewById(R.id.username);
        address=findViewById(R.id.address);
        profile_image.setOnClickListener(this);
        storageRef.child(FirebaseAuth.getInstance().getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(EditActivity.this).load(uri).into(profile_image);
            }
        });
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("users").document(FirebaseAuth.getInstance().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    User user=task.getResult().toObject(User.class);
                    Log.e("name",user.getName());
                    Log.e("email",user.getEmail());
                    username.setText(user.getName());
                    address.setText(user.getEmail());
                }
            }
        });


//        pass=findViewById(R.id.pass);
//        username.setText(user.getName());
//        address.setText(user.getEmail());
//        pass.setText(user.getPassword());
        save = findViewById(R.id.profile_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateDataToFirebase();
                }
                catch (Exception e)
                {
                    Log.e("msg",e.getMessage());
                }
            }

            private void updateDataToFirebase() {
                Log.e("imageuri", String.valueOf(capImageURI));
                final StorageReference storageReference=storageRef.child(FirebaseAuth.getInstance().getUid());
                final UploadTask uploadTask=storageReference.putFile(capImageURI);
                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful())
                        {

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    DocumentReference reference=firebaseFirestore.collection("users").document(FirebaseAuth.getInstance().getUid());
                                    reference.update("name",username.getText().toString());
                                    reference.update("image",String.valueOf(uri));
                                    reference.update("email",address.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(EditActivity.this,"Successfuly updated",Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(EditActivity.this,"Not updated",Toast.LENGTH_SHORT).show();;
                                            }
                                        }
                                    });

                                }
                            });



                        }
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.profile_image:
                onProfileImage();

        }
    }

    private void onProfileImage() {

        final CharSequence[] items = {"Take a new photo", "Choose from gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (items[i].equals("Take a new photo")) {
                    //request permission start camera intent
                    requestCameraPermission();

                } else if (items[i].equals("Choose from gallery")) {
                    //request gallery permission and start gallery intent
                    requestGalleryPermission();

                } else if (items[i].equals("Cancel")) {
                    //dismiss the alert dialog
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    private void requestGalleryPermission() {
        int result = ContextCompat
                .checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat
                    .requestPermissions(EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            GALLERY_REQUEST);
        }
    }

    private void requestCameraPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(EditActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    Toast.makeText(this, "Permission denied to open Camera", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case GALLERY_REQUEST:  {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    Toast.makeText(this, "Permission denied to open Gallery", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }


    }

    private void openGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, GALLERY_REQUEST);
    }

    private void startCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, CAMERA_REQUEST);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GALLERY_REQUEST:{
                if (resultCode == RESULT_OK && data.getData() != null) {
                    capImageURI = data.getData();
                    setProfileImage(data.getData());
                } else {
                    Toast.makeText(this, "No Image Selected! Try AgainNo Image Selected! Try Again", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case CAMERA_REQUEST: {
                if (resultCode == RESULT_OK && data.getData() != null) {
                    capImageURI = data.getData();
                    setProfileImage(data.getData());
                } else {
                    Toast.makeText(this, "No Image Captured! Try Again", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void setProfileImage(Uri imageURI) {

        profile_image.setImageURI(imageURI);


    }
}

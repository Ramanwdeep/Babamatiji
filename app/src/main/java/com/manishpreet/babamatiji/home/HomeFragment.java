package com.manishpreet.babamatiji.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.manishpreet.babamatiji.Post;
import com.manishpreet.babamatiji.R;

import com.manishpreet.babamatiji.profile.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerview;
    PostAdapter adapter;
    FirebaseFirestore firebaseFirestore;
    ImageButton btn_notif,btn_profile;



    public static HomeFragment instance;

    public static HomeFragment getInstance()
    {
        if (instance == null)
        {
            instance = new HomeFragment();
        }
        return instance;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        recyclerview=view.findViewById(R.id.recyclerview);



        btn_profile=view.findViewById(R.id.btn_profile);

        adapter = new PostAdapter(getContext());
        recyclerview.setAdapter(adapter);
        getPosts();

    }

    private void getPosts() {
        firebaseFirestore.collection("posts").orderBy("timestemp", Query.Direction.DESCENDING).get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (int i = 0; i < task.getResult().size(); i++) {
                        Post post = task.getResult().getDocuments().get(i).toObject(Post.class);
                        adapter.add(post,task.getResult().getDocuments().get(i).getId());

                        adapter.notifyDataSetChanged();
                    }
                }
            }

        });
    btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });


    }


}

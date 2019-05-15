package com.manishpreet.babamatiji.about;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manishpreet.babamatiji.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;





    public GalleryFragment() {
        // Required empty public constructor
    }


    public static GalleryFragment instance;

    public static GalleryFragment getInstance()
    {
        if (instance == null)
        {
            instance = new GalleryFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.his_recycler_view);
        adapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }
}

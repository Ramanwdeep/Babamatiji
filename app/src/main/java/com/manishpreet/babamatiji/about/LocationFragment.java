package com.manishpreet.babamatiji.about;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manishpreet.babamatiji.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements View.OnClickListener {

    private TextView tvPhone;
    private ImageView ivMap;



    public LocationFragment() {
        // Required empty public constructor
    }

    public static LocationFragment instance;

    public static LocationFragment getInstance()
    {
        if (instance == null)
        {
            instance = new LocationFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tvPhone = view.findViewById(R.id.tv_phone);
        ivMap = view.findViewById(R.id.iv_map_location);


        tvPhone.setOnClickListener(this);
        ivMap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.tv_phone:
            {
                makePhoneCall();
                break;
            }
            case R.id.iv_map_location:
            {
                openGoogleMaps();
                break;
            }


        }
    }

    private void makePhoneCall() {
        String phone = tvPhone.getText().toString().trim();
        Intent makeCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(makeCall);

    }

    private void openGoogleMaps() {
        String mapUrl = "https://www.google.com/maps/place/Gurudwara+Shaheed+Baba+Mati/@31.4110776,75.7855223,17z/data=!4m5!3m4!1s0x391af9976391f6d9:0xbeda91cdd421c8e0!8m2!3d31.4107938!4d75.787711";
        Intent openMap = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
        startActivity(openMap);

    }




}

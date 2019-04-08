package com.manishpreet.babamatiji.bani;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manishpreet.babamatiji.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaniFragment extends Fragment {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    public BaniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bani, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drawerLayout = view.findViewById(R.id.bani_drawer_layout);
        navigationView = view.findViewById(R.id.bani_navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.bani_1:
                    {
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.bani_2:
                    {
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.bani_3:
                    {
                        Toast.makeText(getActivity(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    }





                    default:
                    {
                        return false;
                    }
                }


                return true;
            }
        });







    }
}

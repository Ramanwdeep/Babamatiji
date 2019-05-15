package com.manishpreet.babamatiji;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.manishpreet.babamatiji.about.AboutFragment;
import com.manishpreet.babamatiji.bani.BaniFragment;
import com.manishpreet.babamatiji.createpost.CreatePost;
import com.manishpreet.babamatiji.events.EventsFragment;
import com.manishpreet.babamatiji.home.HomeFragment;

import com.manishpreet.babamatiji.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private FloatingActionButton fab;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        navigationView = findViewById(R.id.bottom_navigation);
        fab.setOnClickListener(this);
        navigationView.setOnNavigationItemSelectedListener(MainActivity.this);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        //String currentUserMail = auth.getCurrentUser().getEmail();

        //if (currentUserMail.equals("ramandeep@gmail.com")||currentUserMail.equals("davinder@gmail.com"))
        //{fab.setVisibility(View.VISIBLE);}
        //else {fab.setVisibility(View.INVISIBLE);}

        loadDefaultFragment();
    }


    // inflating the menu file for action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }


    // action listener for action bar menu icons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.item_profile:
            {
                // open profile activity
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            }
        }


        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab:
            {
                startActivity(new Intent(MainActivity.this, CreatePost.class));
                break;
            }
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment;

        switch (menuItem.getItemId())
        {
            case R.id.nav_item_home:
            {
                fragment = HomeFragment.getInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.nav_item_events:
            {
                fragment = EventsFragment.getInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.nav_item_bani:
            {
                fragment = BaniFragment.getInstance();
                replaceFragment(fragment);
                break;
            }
            case R.id.nav_item_About:
            {
                fragment = AboutFragment.getInstance();
                replaceFragment(fragment);
                break;
            }
        }


        return true;
    }


    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void loadDefaultFragment()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new HomeFragment());
        transaction.addToBackStack("abc");
        transaction.commit();
    }



}

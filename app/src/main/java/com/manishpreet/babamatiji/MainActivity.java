package com.manishpreet.babamatiji;

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

import com.manishpreet.babamatiji.about.AboutFragment;
import com.manishpreet.babamatiji.bani.BaniFragment;
import com.manishpreet.babamatiji.createpost.CreatePost;
import com.manishpreet.babamatiji.events.EventsFragment;
import com.manishpreet.babamatiji.home.HomeFragment;
import com.manishpreet.babamatiji.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_navigation);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(this);
        navigationView.setOnNavigationItemSelectedListener(MainActivity.this);

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
            case R.id.item_message:
            {
                // open message activity

                break;
            }
            case R.id.item_notification:
            {
                // open notifications activity

                break;
            }

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

        switch (menuItem.getItemId())
        {
            case R.id.nav_item_home:
            {
                replaceFragment(new HomeFragment());
                break;
            }
            case R.id.nav_item_events:
            {
                replaceFragment(new EventsFragment());
                break;
            }
            case R.id.nav_item_bani:
            {
                replaceFragment(new BaniFragment());
                break;
            }
            case R.id.nav_item_About:
            {
                replaceFragment(new AboutFragment());
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
        transaction.commit();
    }



}

package com.manishpreet.babamatiji.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.manishpreet.babamatiji.App;
import com.manishpreet.babamatiji.MainActivity;
import com.manishpreet.babamatiji.Prefrences;
import com.manishpreet.babamatiji.R;

public class SplashActivity extends AppCompatActivity {

FirebaseAuth auth;
//User user;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
//user=Prefrences.getUser(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              // FirebaseUser user=auth.getCurrentUser();
                if (user != null)
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);

    }
}

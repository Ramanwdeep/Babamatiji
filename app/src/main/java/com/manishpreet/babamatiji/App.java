package com.manishpreet.babamatiji;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;

public class App extends Application {
   public static SharedPreferences prefrences;
    public static  SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        prefrences=getSharedPreferences("mansihd",MODE_PRIVATE);
        editor=prefrences.edit();
    }
}

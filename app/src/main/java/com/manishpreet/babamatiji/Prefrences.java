package com.manishpreet.babamatiji;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.manishpreet.babamatiji.profile.User;

import static android.content.Context.MODE_PRIVATE;

public class Prefrences {
   public static String user_key="user";
   static SharedPreferences prefrences;
   static SharedPreferences.Editor editor;

  public static void saveUser(Context context,User user)
    {
        prefrences=context.getSharedPreferences("mansihd",MODE_PRIVATE);
        editor=prefrences.edit();
        String u= new Gson().toJson(user);
        editor.putString(user_key,u);
        editor.commit();
    }

  public static User getUser(Context context)
    {
        prefrences=context.getSharedPreferences("mansihd",MODE_PRIVATE);
        editor=prefrences.edit();
        String u=prefrences.getString(user_key,null);
        User user=new Gson().fromJson(u,User.class);
        return user;
    }

    public static void clearPref(Context context)
    {
        prefrences=context.getSharedPreferences("mansihd",MODE_PRIVATE);
        editor=prefrences.edit();
        editor.clear().commit();
    }
}

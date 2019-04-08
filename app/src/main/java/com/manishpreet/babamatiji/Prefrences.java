package com.manishpreet.babamatiji;

import com.google.gson.Gson;
import com.manishpreet.babamatiji.profile.User;

public class Prefrences {
   public static String user_key="user";


  public static void saveUser(User user)
    {
        String u= new Gson().toJson(user);
        App.editor.putString(user_key,u);
        App.editor.commit();
    }

  public static User getUser()
    {
        String u=App.prefrences.getString(user_key,null);
        User user=new Gson().fromJson(u,User.class);
        return user;
    }
}

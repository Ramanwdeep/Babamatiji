package com.manishpreet.babamatiji;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class Post {
    public Post(String description, String image) {
        this.Image=image;
        this.timestemp= new Date().getTime();
        this.Uid= FirebaseAuth.getInstance().getUid();
        this.Desc=description;

    }

    public Post(){

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Long getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(Long timestemp) {
        this.timestemp = timestemp;
    }

    String Name;
    String Desc;
    String Image;
    String Uid;
    Long timestemp;

}

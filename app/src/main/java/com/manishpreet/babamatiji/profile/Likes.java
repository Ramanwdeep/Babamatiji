package com.manishpreet.babamatiji.profile;

import java.util.Date;

public class Likes {

    String post_id;

    public Likes(){

    }
    public Likes(String uid, String s) {
        this.uid=uid;
        this.post_id=s;
        timestemp=new Date().getTime();
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(long timestemp) {
        this.timestemp = timestemp;
    }

    String uid;
    long timestemp;
}

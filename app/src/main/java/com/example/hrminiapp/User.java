package com.example.hrminiapp;

import java.io.Serializable;

public class User implements Serializable {
    String userid, emailid;
    public User(String uid, String email)
    {
        this.userid = uid;
        this.emailid = email;
    }
}

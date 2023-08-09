package com.example.hrminiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    TextView txtWelcome;
    SharedPreferences sharedPref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }
    private void init()
    {
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
//        Intent intent1 = getIntent();
//        String userName = intent1.getStringExtra("USER_ID");
//        txtWelcome.setText("Welcome " + userName + " !");

//        Intent intent1 = getIntent();
//        User userObj = (User)intent1.getSerializableExtra("USER_DETAILS");
//        txtWelcome.setText("Welcome " + userObj.userid + " !" + "\n" + userObj.emailid);

        sharedPref1= getSharedPreferences("login_details", Context.MODE_PRIVATE);
        txtWelcome.setText("Welcome " + sharedPref1.getString("USER_ID",null) + "\n" +
                sharedPref1.getString("EMAIL_ID", null));
    }
}
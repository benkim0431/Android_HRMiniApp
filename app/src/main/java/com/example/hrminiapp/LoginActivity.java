package com.example.hrminiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUid, edtEmail, edtPwd;
    Button btnLogin;
    Intent intent1;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        edtUid = (EditText) findViewById(R.id.edtUid);
        edtEmail = (EditText) findViewById(R.id.edtEmailId);
        edtPwd = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        // Get SharedPerference to share data with other comtent
        sharedPref1 = getSharedPreferences("login_details", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // validate id and password
                if(edtUid.getText().toString().trim().equals("thomas") &&
                        edtPwd.getText().toString().trim().equals("test")){
                    intent1 = new Intent(LoginActivity.this, HomeActivity.class);

//                    User objUser = new User(edtUid.getText().toString().trim(),
//                            edtEmail.getText().toString().trim());
////                    intent1.putExtra("USER_ID", edtesttUid.getText().toString().trim());
//                    intent1.putExtra("USER_DETAILS", objUser);

                    // Using sharedpreference send data to another content
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("USER_ID", edtUid.getText().toString().trim());
                    editor1.putString("EMAIL_ID", edtEmail.getText().toString().trim());
                    editor1.commit();

                    startActivity(intent1);
                }
                else{
                    //We will invoke an alert dialog to notify unsuccessful login
                    //after we learn about dialog boxes
                    Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_LONG).show();

                    InvalidDialog dialog1 = new InvalidDialog();
                    dialog1.show(getSupportFragmentManager(),"invalid");
                }
            }
        });
    }
}
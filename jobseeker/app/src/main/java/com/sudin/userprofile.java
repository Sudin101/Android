package com.sudin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.R;

public class userprofile extends AppCompatActivity {
    Intent intent;
    String cookie;
    private final static  String BASE_URL= "http://192.168.1.67:3000/";

    private TextView username,email,number,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile2);
        intent= getIntent();
        cookie = intent.getStringExtra("mysession");
        Toast.makeText(this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
    }
}

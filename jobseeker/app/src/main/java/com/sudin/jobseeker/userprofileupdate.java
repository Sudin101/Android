package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class userprofileupdate extends AppCompatActivity {
    public final static String MESSAGE_KEY ="sudin.senddata.message_key";
    Intent intent;
    String cookie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofileupdate);

        cookie = intent.getStringExtra("cookie");
        Intent intent = getIntent();
        Toast.makeText(this, "post ma toast  "+ cookie, Toast.LENGTH_SHORT).show();
        String message = intent.getStringExtra(MESSAGE_KEY);
        Toast.makeText(this, "a"+ message, Toast.LENGTH_SHORT).show();
    }
}

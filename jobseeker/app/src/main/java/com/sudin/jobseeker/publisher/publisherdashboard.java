package com.sudin.jobseeker.publisher;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.sudin.jobseeker.Post;
import com.sudin.jobseeker.R;
import com.sudin.jobseeker.jobpostpublisher;
import com.sudin.jobseeker.showjobpost;
import com.sudin.jobseeker.userprofile;

public class publisherdashboard extends AppCompatActivity {
    Intent intent;
    String cookie;

    RelativeLayout relaativelayouthome,relaativelayoutprofile,relaativelayoutjob,relaativelayoutjobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisherdashboard2);

        intent= getIntent();
        getNetworkType();
        cookie = intent.getStringExtra("mysession");

        Toast.makeText(publisherdashboard.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
        relaativelayoutjobs = findViewById(R.id.jobs);


        relaativelayouthome = findViewById(R.id.relaativelayouthome);
        relaativelayoutjob = findViewById(R.id.job);
        relaativelayoutprofile = findViewById(R.id.profile);

        relaativelayoutprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(publisherdashboard.this, userprofile.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

        relaativelayouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(publisherdashboard.this, Post.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

        relaativelayoutjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(publisherdashboard.this, jobpostpublisher.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });
        relaativelayoutjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(publisherdashboard.this, showjobpost.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

    }
    public String getNetworkType(){
        String networkType = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                networkType = "WiFi";

                Toast.makeText(this, "you are using ho wifi", Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkType = "Mobile";
                Toast.makeText(this, "you are using mobile data", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Not connected to Internet", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
        return networkType;
    }
}

package com.sudin.jobseeker;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    Intent intent;
    String cookie;


    RelativeLayout relaativelayouthome,relaativelayoutprofile,relaativelayoutjob,relaativelayoutjobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        intent= getIntent();
        getNetworkType();
        cookie = intent.getStringExtra("mysession");

        Toast.makeText(DashboardActivity.this, "cookie received" +cookie, Toast.LENGTH_SHORT).show();
        relaativelayoutjobs = findViewById(R.id.jobs);

        relaativelayouthome = findViewById(R.id.relaativelayouthome);
        relaativelayoutjob = findViewById(R.id.job);
        relaativelayoutprofile = findViewById(R.id.profile);


        relaativelayoutprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DashboardActivity.this, userprofile.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

        relaativelayouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DashboardActivity.this, Post.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });

        relaativelayoutjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DashboardActivity.this, jobpostpublisher.class);
                intent.putExtra("cookie",cookie);
                startActivity(intent);
            }
        });
        relaativelayoutjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DashboardActivity.this, showjobpost.class);
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

                Toast.makeText(this, "you are using wifi", Toast.LENGTH_SHORT).show();
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

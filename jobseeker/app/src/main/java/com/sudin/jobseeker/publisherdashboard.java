package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class publisherdashboard extends AppCompatActivity {

    Intent intent;
    String cookie;

    RelativeLayout relaativelayouthome,relaativelayoutprofile,relaativelayoutjob,relaativelayoutjobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        intent= getIntent();

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
}

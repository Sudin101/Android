package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.models.ltems;

public class showfulljobpost  extends AppCompatActivity {
    private ImageView imagee;
    private TextView namee, pricee, description,categorye;
    private Button btn_updatepost;
    ActionBar actionBar;
    String cookie;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showfulljobpost);

        actionBar = getSupportActionBar();
        actionBar.setTitle("job seeker");
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
//        Toast.makeText(showfulljobpost.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
//        imagee = findViewById(R.id.iv_photo);
        namee = findViewById(R.id.tv_name);
        pricee = findViewById(R.id.tv_price);
        categorye = findViewById(R.id.categoryes);

        description = findViewById(R.id.tv_description);
        btn_updatepost = findViewById(R.id.btn_addpost);




//        ltems bundle = (ltems) getIntent().getSerializableExtra("Editing");

        ltems model = (ltems) getIntent().getSerializableExtra("datasingle");
      Toast.makeText(this, model.toString(), Toast.LENGTH_LONG).show();

        if (model != null) {
            namee.setText(model.getName());
            pricee.setText(model.getApplicant());
            description.setText(model.getDescription());
            categorye.setText(model.getDeadline());

        }

    }
}

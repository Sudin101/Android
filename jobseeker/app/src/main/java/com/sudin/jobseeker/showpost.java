package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.models.Post;

public class showpost extends AppCompatActivity {
    private ImageView imagee;
    private TextView namee, pricee, description;
    private Button btn_updatepost;
    ActionBar actionBar;
    String cookie;
   Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpost);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
        Toast.makeText(showpost.this, "cookie tracked" +cookie, Toast.LENGTH_SHORT).show();
        imagee = findViewById(R.id.iv_photo);
        namee = findViewById(R.id.tv_name);
        pricee = findViewById(R.id.tv_price);
        description = findViewById(R.id.tv_description);

        Post model = (Post) getIntent().getSerializableExtra("datasingle");
        Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();

        if(model !=null){

            namee.setText(model.getDescription());


        }
    }
}

package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.models.ltems;

public class jobpostsfinal extends AppCompatActivity {
    Intent intent;
    String cookie;
    private ImageView imagee;
    private TextView namee, pricee, description,catogory,id;
    private Button btn_updatepost,apply;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobposts);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");

        Toast.makeText(jobpostsfinal.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
        imagee = findViewById(R.id.iv_photo);
        namee = findViewById(R.id.tv_name);
        pricee = findViewById(R.id.tv_price);
        id = findViewById(R.id.id);
        catogory = findViewById(R.id.categoryes);
        apply = findViewById(R.id.apply);
        description = findViewById(R.id.tv_description);
        btn_updatepost = findViewById(R.id.btn_addpost);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(jobpostsfinal.this, jobapply.class);
                intent.putExtra("cookie",cookie);
              intent.putExtra("names",namee.getText());
                intent.putExtra("catogory",catogory.getText());
                intent.putExtra("pricee",pricee.getText());
                intent.putExtra("id",id.getText());
//                intent.putExtra("description",description.getText());

                startActivity(intent);
            }
        });

//
//
//
////        ltems bundle = (ltems) getIntent().getSerializableExtra("Editing");
//
        ltems model = (ltems) getIntent().getSerializableExtra("datasingle");
//        Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
//
//
//
//
        if (model != null) {

            id.setText(model.get_id());
            namee.setText(model.getName());
            catogory.setText(model.getDeadline());
            pricee.setText(model.getApplicant());
            description.setText(model.getDescription());

        }

    }
}

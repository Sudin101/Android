package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sudin.jobseeker.models.ltems;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class publisherfulljobpost extends AppCompatActivity {

    private ImageView imagee;
    private EditText namee, pricee, description,category,deadline;
    private Button btn_updatepost;
    ActionBar actionBar;
    String cookie;
    Intent intent;
    String BASE_URL = "http://192.168.1.67:3000/";
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();
    com.sudin.jobseeker.Interface.jobpostinterface jobpostinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisherfulljobpost);
        actionBar = getSupportActionBar();
        actionBar.setTitle("update");
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
        Toast.makeText(publisherfulljobpost.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
        imagee = findViewById(R.id.iv_photo);
        namee = findViewById(R.id.postname);
        category = findViewById(R.id.category);
        deadline = findViewById(R.id.deadline);
        pricee = findViewById(R.id.applicantno);
        description = findViewById(R.id.description);
        imagee = findViewById(R.id.image);
        btn_updatepost = findViewById(R.id.btn_addpost);
        ltems model = (ltems) getIntent().getSerializableExtra("datasingle");
//        Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();

        if(model !=null){

           namee.setText(model.getName());
            pricee.setText(model.getApplicant());
            description.setText(model.getDescription());
            deadline.setText(model.getDeadline());
            category.setText(model.getJobType());

        }



    }
//    private void createInstance() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        jobpostinterface = retrofit.create(jobpostinterface.class);
//    }
//
//    private void addItem() {
//
//        String itemNameFinal = namee.getText().toString();
//        String itemApplicant = pricee.getText().toString();
//        String itemdescription = description.getText().toString();
//        String itemdeadline = deadline.getText().toString();
//        String itemcategory = category.getText().toString();
//
//
//        com.bibash.jobseeker.models.ltems item = new com.bibash.jobseeker.models.ltems(itemNameFinal,itemApplicant,itemdescription,itemdeadline,itemcategory);
//
//        Call<Void> voidCall = jobpostinterface.updateItem(cookie,item,);
//        voidCall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(Post.this, "Item Added Successfully ", Toast.LENGTH_LONG).show();
//
//                if(response.isSuccessful()){
//
//                    Toast.makeText(Post.this, "Sucessfull vayo", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(Post.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
}

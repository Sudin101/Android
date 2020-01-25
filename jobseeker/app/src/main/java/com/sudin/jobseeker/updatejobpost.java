package com.sudin.jobseeker;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sudin.jobseeker.Interface.jobpostinterface;
import com.sudin.jobseeker.models.ltems;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class updatejobpost extends AppCompatActivity  {
    private Button confirmImage, btn_addpost;
    private EditText postname, applicantno, deadline,category,description;
    private TextView imageName;
    private ImageView itemImage;
    ActionBar actionBar;
    Uri imageUri;
    String cookie;
    Intent intent;
    Bitmap bitmap;
    Retrofit retrofit;
    jobpostinterface jobpostinterface;
    private static final int PICK_IMAGE = 1;
    public static final String BASE_URL ="http://192.168.1.67:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobpost);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");

        initializeItems();
    }
    private void initializeItems() {
        postname = findViewById(R.id.postname);
        applicantno = findViewById(R.id.applicantno);

        deadline = findViewById(R.id.deadline);
        category = findViewById(R.id.category);
        imageName = findViewById(R.id.imageName);
        description = findViewById(R.id.description);

        itemImage = findViewById(R.id.iv_image);

        confirmImage = findViewById(R.id.btn_confirmImage);
        btn_addpost = findViewById(R.id.btn_addpost);

        actionBar = getSupportActionBar();
        actionBar.setTitle("jobseeker");
    }


    private void createInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jobpostinterface = retrofit.create(jobpostinterface.class);
    }

    private void updateItem() {
        createInstance();

        String itemNameFinal = postname.getText().toString();
        String applicantnoFinal = applicantno.getText().toString();

        String itemDescriptionFinal = description.getText().toString();
        String imagedeadline = deadline.getText().toString();
        String itemcategory = category.getText().toString();

        ltems item = new ltems(itemNameFinal, applicantnoFinal, itemDescriptionFinal,imagedeadline,itemcategory);

        Call<Void> voidCall = jobpostinterface.addItem(cookie,item);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(updatejobpost.this, "Item Added Successfully ", Toast.LENGTH_LONG).show();

                if(response.isSuccessful()){

                    Toast.makeText(updatejobpost.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(updatejobpost.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_menubar, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.back:

                Intent back = new Intent(updatejobpost.this, DashboardActivity.class);
                startActivity(back);

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean Validate() {


        if (TextUtils.isEmpty(postname.getText().toString())) {
            postname.setError("Enter Item Name");
            postname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(applicantno.getText().toString())) {
            applicantno.setError("Enter Price");
            applicantno.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(description.getText().toString())) {
            description.setError("Enter item description");
            description.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(deadline.getText().toString())) {
            deadline.setError("Enter item description");
            deadline.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(category.getText().toString())) {
            category.setError("Enter item description");
            category.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(category.getText().toString())) {
            category.setError("Enter item description");
            category.requestFocus();
            return false;
        } else {
            return true;

        }
    }

}

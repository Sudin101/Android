package com.sudin.jobseeker;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sudin.jobseeker.Interface.jobpostinterface;
import com.sudin.jobseeker.models.ltems;
import com.sudin.jobseeker.models.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class Updatejob extends AppCompatActivity implements View.OnClickListener {
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
    ltems model;
    jobpostinterface jobpostinterface;
    private static final int PICK_IMAGE = 1;
    public static final String BASE_URL = "http://192.168.1.67:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobpost);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
        Toast.makeText(this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();

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

        confirmImage.setOnClickListener(this);
        btn_addpost.setOnClickListener(this);
        itemImage.setOnClickListener(this);


        actionBar = getSupportActionBar();
        actionBar.setTitle("jobseeker");
        ltems model = (ltems) getIntent().getSerializableExtra("datasingle");
        Toast.makeText(this, model.get_id(), Toast.LENGTH_SHORT).show();

        if (model != null) {

            postname.setText(model.getName());
            applicantno.setText(model.getApplicant());
            description.setText(model.getDescription());
            category.setText(model.getJobType());
            deadline.setText(model.getDeadline());


        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_confirmImage:
                addItem();
                break;

            case R.id.btn_addpost:
                addImage(bitmap);
                btn_addpost.setEnabled(true);
                break;


        }

    }
    private void openGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), imageUri);

                itemImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void createInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jobpostinterface = retrofit.create(jobpostinterface.class);
    }
    private void addImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            RequestBody requestBody = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("imageFile", file.getName(), requestBody);


            createInstance();
            Call<Image> imgCall = jobpostinterface.uploadImage(body);
            imgCall.enqueue(new Callback<Image>() {
                @Override
                public void onResponse(Call<Image> call, Response<Image> response) {
                    imageName.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<Image> call, Throwable t) {
                    Toast.makeText(Updatejob.this, "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addItem() {
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
                Toast.makeText(Updatejob.this, "Item Added Successfully ", Toast.LENGTH_LONG).show();

                if(response.isSuccessful()){

                    Toast.makeText(Updatejob.this, "Sucessfull vayo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Updatejob.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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

                Intent back = new Intent(Updatejob.this, DashboardActivity.class);
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

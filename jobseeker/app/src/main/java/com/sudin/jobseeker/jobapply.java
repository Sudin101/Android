package com.sudin.jobseeker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.Interface.applyinterface;
import com.sudin.jobseeker.models.Image;
import com.sudin.jobseeker.models.apply;

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

public class jobapply extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    String cookie;
    private Button confirmImage, btn_addpost;
    private TextView imageName,id;
    private ImageView itemImage;
    Bitmap bitmap;
    Uri imageUri;
    Retrofit retrofit;
    com.sudin.jobseeker.Interface.applyinterface applyinterface;
    private static final int PICK_IMAGE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String BASE_URL ="http://192.168.1.67:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobapply);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
        initializeItems();
        btn_addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
addItem();
            }
        });

//        Toast.makeText(jobapply.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, "ayo ki nai "+bundle, Toast.LENGTH_SHORT).show();
        if (bundle != null) {

            id.setText(bundle.getString("id"));


        }
    }
    private void initializeItems() {
        imageName = findViewById(R.id.imageName);
        itemImage = findViewById(R.id.iv_image);
        confirmImage = findViewById(R.id.btn_confirmImage);
        btn_addpost = findViewById(R.id.btn_apply);
        id = findViewById(R.id.id);
        confirmImage.setOnClickListener(this);
        btn_addpost.setOnClickListener(this);
        itemImage.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.iv_image:
                openGallery();
                break;

            case R.id.btn_confirmImage:
                addImage(bitmap);
                btn_addpost.setEnabled(true);
                break;

            case R.id.btn_addpost:
                Toast.makeText(this, "ho ki nai ", Toast.LENGTH_SHORT).show();
                if (Validate() == true) {
                    addItem();
                } else {
                    Toast.makeText(jobapply.this, "Empty Fields", Toast.LENGTH_LONG).show();
                }
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

        applyinterface = retrofit.create(applyinterface.class);
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
            Call<Image> imgCall = applyinterface.uploadImage(body);
            imgCall.enqueue(new Callback<Image>() {
                @Override
                public void onResponse(Call<Image> call, Response<Image> response) {
                    imageName.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<Image> call, Throwable t) {
                    Toast.makeText(jobapply.this, "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
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

        String itemNameFinal = imageName.getText().toString();
        String applicantnoFinal = id.getText().toString();



        apply item = new apply(applicantnoFinal,itemNameFinal);

        Call<Void> voidCall = applyinterface.jobapply(cookie,item);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(jobapply.this, "Added Successfully ", Toast.LENGTH_LONG).show();

                if(response.isSuccessful()){
                    finish();
                    Toast.makeText(jobapply.this, "Sucessfull vayo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(jobapply.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private boolean Validate() {


        if (TextUtils.isEmpty(imageName.getText().toString())) {
            imageName.setError("Enter Item Name");
            imageName.requestFocus();
            return false;

        } else {
            return true;

        }
    }
}

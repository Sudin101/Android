package com.sudin.jobseeker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.Interface.usersinterfaces;
import com.sudin.jobseeker.models.Image;
import com.sudin.jobseeker.models.users;

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

public class profiles extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    String cookie;
    private Button confirmImage, addItem,btn_addpost;
    private EditText fname, email, country,username,dob,number,address;
    private TextView imageName;
    private ImageView itemImage;
    Bitmap bitmap;
    Uri imageUri;
    Retrofit retrofit;
    com.sudin.jobseeker.Interface.usersinterfaces usersinterfaces;
    private static final int PICK_IMAGE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static final String BASE_URL ="http://192.168.1.67:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        intent= getIntent();
        cookie = intent.getStringExtra("cookie");
        Toast.makeText(profiles.this, "cookie ayo  " +cookie, Toast.LENGTH_SHORT).show();

        initializeItems();
    }
    private void initializeItems() {
        fname = findViewById(R.id.fname);
        email = findViewById(R.id.email);

        username = findViewById(R.id.username);
        address = findViewById(R.id.address);
        imageName = findViewById(R.id.imageName);
        number = findViewById(R.id.number);

        itemImage = findViewById(R.id.iv_image);

        confirmImage = findViewById(R.id.btn_confirmImage);
        btn_addpost = findViewById(R.id.btn_addpost);

        confirmImage.setOnClickListener(this);
        btn_addpost.setOnClickListener(this);
        itemImage.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            fname.setText(bundle.getString("fname"));
            email.setText(bundle.getString("email"));
            username.setText(bundle.getString("username"));
            address.setText(bundle.getString("address"));
//
            number.setText(bundle.getString("number"));
            imageName.setText(bundle.getString("address"));
        }

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

                    addItem();

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

        usersinterfaces = retrofit.create(usersinterfaces.class);
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
            Call<Image> imgCall = usersinterfaces.uploadImage(body);
            imgCall.enqueue(new Callback<Image>() {
                @Override
                public void onResponse(Call<Image> call, Response<Image> response) {
                    imageName.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<Image> call, Throwable t) {
                    Toast.makeText(profiles.this, "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
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

        String itemNameFinal = fname.getText().toString();
        String applicantnoFinal = email.getText().toString();
        String itemDescriptionFinal = number.getText().toString();
        String imagedeadline = username.getText().toString();
        String itemcategory = imageName.getText().toString();

        users item = new users(itemNameFinal,itemDescriptionFinal, applicantnoFinal,itemcategory, imagedeadline);
        Toast.makeText(this, "data"+ item, Toast.LENGTH_SHORT).show();
        Call<Void> voidCall = usersinterfaces.updateItem(cookie,item);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(profiles.this, "Item Added Successfully ", Toast.LENGTH_LONG).show();

                if(response.isSuccessful()){

                    Toast.makeText(profiles.this, "Sucessfull vayo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(profiles.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

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

                Intent back = new Intent(profiles.this, DashboardActivity.class);
                startActivity(back);

                break;

        }
        return super.onOptionsItemSelected(item);
    }



}

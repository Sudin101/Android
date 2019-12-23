package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.Interface.Authentication;
import com.sudin.jobseeker.models.users;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userprofile extends AppCompatActivity {
    Intent intent;
    String cookie;
    TextView email,country,username,number,dob,fname;
    ImageView images;
    Button btn_update;
    public final static String MESSAGE_KEY ="sudin.senddata.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        intent= getIntent();
        email = findViewById(R.id.email);
        country = findViewById(R.id.country);
        username = findViewById(R.id.username5);
        number = findViewById(R.id.number5);
        images = findViewById(R.id.image);
        dob = findViewById(R.id.dob);
        fname = findViewById(R.id.fname);
        btn_update = findViewById(R.id.btn_userupdate);
        cookie = intent.getStringExtra("cookie");

        Toast.makeText(this, "post ma tost  "+ cookie, Toast.LENGTH_SHORT).show();
        getUser();
        Toast.makeText(this, "sas" , Toast.LENGTH_SHORT).show();
        final String message = email.getText().toString();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(userprofile.this, profiles.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("cookie",cookie);
                intent.putExtra("fname",fname.getText());
                intent.putExtra("email",email.getText());
                intent.putExtra("number",number.getText());
                intent.putExtra("username",username.getText());
                startActivity(intent);
            }
        });
    }


    public void getUser(){

        Authentication userInterface = url.getInstance().create(Authentication.class);
        final Call<users> hello = userInterface.getuserdata(cookie);
        hello.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {


                String text = "";
                String image = response.body().getImage();
                Picasso.with(userprofile.this).load("http://192.168.1.67:3000/uploads/" + image).into(images);
                Toast.makeText(userprofile.this,"a" +image, Toast.LENGTH_SHORT).show();
                            email.setText(response.body().getEmail());
               username.setText(response.body().getUsername());
                country.setText(response.body().getAddress());
              number.setText(response.body().getContactnumber());
                dob.setText(response.body().getDob());
                fname.setText(response.body().getFirstName());

                text += "First name : " + response.body().getEmail() + "\n";
                text += "Username : " + response.body().getUsername() + "\n";
                text += "Email : " + response.body().getEmail() + "\n";
                text += "Contact No : " + response.body().getContactnumber() + "\n";
                text += "image : " + response.body().getImage() + "\n";

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });


    }
}

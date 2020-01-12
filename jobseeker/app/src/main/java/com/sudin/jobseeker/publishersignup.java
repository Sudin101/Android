package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.sudin.jobseeker.BLL.RegisterBLLs;
import com.sudin.jobseeker.StrictMode.StrictModeActivity;

public class publishersignup extends AppCompatActivity {
    EditText publisher,firstname,lastname,username,password;
    Button regbutton,gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishersignup);

        firstname=findViewById(R.id.fullname);
        lastname=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        regbutton=findViewById(R.id.btnjoin);

        publisher=findViewById(R.id.publisher);
        publisher.setVisibility(View.INVISIBLE);
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    private void signup(){

        Log.d("hello", "Click bhayo!");


        StrictModeActivity.StrictMode();

        RegisterBLLs registerBLLs = new RegisterBLLs(username.getText().toString(),firstname.getText().toString(), lastname.getText().toString(),password.getText().toString(),publisher.getText().toString());
        if(registerBLLs.registerUser())
        {

            Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(publishersignup.this, LoginActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error login",Toast.LENGTH_SHORT).show();
        }
    }


}

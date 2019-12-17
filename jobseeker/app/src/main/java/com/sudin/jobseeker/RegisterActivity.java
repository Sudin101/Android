package com.sudin.jobseeker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sudin.jobseeker.BLL.RegisterBLL;
import com.sudin.jobseeker.StrictMode.StrictModeActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText firstname,lastname,username,password;
    Button regbutton,gotologin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname=findViewById(R.id.fullname);
        lastname=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        regbutton=findViewById(R.id.btnjoin);
        gotologin=findViewById(R.id.btn_publisher);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, publishersignup.class);

                startActivity(intent);
            }
        });
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

        RegisterBLL registerBLL = new RegisterBLL(firstname.getText().toString(),lastname.getText().toString(), username.getText().toString(),password.getText().toString());
        if(registerBLL.registerUser())
        {

            Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error login",Toast.LENGTH_SHORT).show();
        }
    }

}

package com.sudin.jobseeker;

import android.app.Notification;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.Interface.Authentication;
import com.sudin.jobseeker.models.User;
import com.sudin.jobseeker.publisher.publisherlogin;
import com.squareup.seismic.ShakeDetector;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sudin.jobseeker.app.CHANNEL_1_ID;

public class LoginActivity extends AppCompatActivity  implements ShakeDetector.Listener {

    private static final String TAG =LoginActivity.class.getSimpleName() ;
    Button btn,btn2,btnsignup,btnpublisher,btnMap;
    Intent intent;
    EditText emailustext, passwordtxt;
    private NotificationManagerCompat notificationManagerCompat;
    Vibrator vibrator;
//    publisherlogin
private NotificationManagerCompat notificationManager;
    String BASE_URL = "http://192.168.1.67:3000/";
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SensorManager sensorManagers = (SensorManager) getSystemService(SENSOR_SERVICE);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        ShakeDetector shakeDetector = new ShakeDetector(this);
        vibrator =(Vibrator) getSystemService(VIBRATOR_SERVICE);
        shakeDetector.start(sensorManagers);
        Log.d("TAG", "message done");
        btn = findViewById(R.id.btnSignin);
        btnsignup = findViewById(R.id.btnsignup);
        btnpublisher = findViewById(R.id.btnpublisher);
        btn2 = findViewById(R.id.btnsignup);
        btnMap = findViewById(R.id.btnMAP);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity2.class);
                startActivity(intent);
            }
        });
       getNetworkType();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailustext=(EditText) findViewById(R.id.emailLogin);
           passwordtxt=(EditText) findViewById(R.id.passwordLogin);
                vibrator.vibrate(50);
                Log.d(TAG,emailustext.getText().toString());
                sendOnChannel1();
                getloginstatus(emailustext.getText().toString(),passwordtxt.getText().toString());
                intent = new Intent(LoginActivity.this, DashboardActivity.class);
                Log.d("TAG", "done");


            }
        });
        btnpublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, publisherlogin.class);
                vibrator.vibrate(50);
                startActivity(intent);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, publisherlogin.class);
                vibrator.vibrate(50);
                startActivity(intent);
            }
        });

btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        vibrator.vibrate(50);
        startActivity(intent);
    }
});


    }
    @Override
    public void hearShake() {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

        startActivity(intent);

    }
    public void sendOnChannel1(){
        CreateNotification channel = new CreateNotification(this);
        channel.createChannel();

        Notification notification = new NotificationCompat.Builder(this, CreateNotification.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Logged In")
                .setContentText("Logged to jobportal system")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
    public String getNetworkType(){
        String networkType = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                networkType = "WiFi";

                Toast.makeText(this, "you are using wifi", Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkType = "Mobile";
                Toast.makeText(this, "you are using mobile data", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Not connected to INternet", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
        return networkType;
    }

    public void getloginstatus(String username, String password){

    User userss=new User(username,password);

        Toast.makeText(this, "Data text filed ko  "+userss.getusername() +userss.getPassword(), Toast.LENGTH_SHORT).show();

            Log.d(TAG, "Entered");

            Authentication authentication = retrofit.create(Authentication.class);
            Call<ResponseBody> call = authentication.loginuser(userss);

            Log.d(TAG, "Login method done");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful())
                    {
                        String session = response.headers().get("Set-Cookie");
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.putExtra("mysession",session);
                        sendOnChannel1();
                        startActivity(intent);
                        Log.d(TAG,response.body().toString());
                        Toast.makeText(LoginActivity.this, "Vayo login", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, "Error login "+t.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();


                }
            });


//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    Log.d(TAG, response.toString());
//                    Log.d(TAG, response.message());
//                    if(!response.isSuccessful())
//                    {
//                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
////                Log.d(TAG, response.body().toString());
////                Toast.makeText(context, "Login vayo", Toast.LENGTH_SHORT).show();
////                returnresponses=response.message().toString();
//                    String mysession = response.headers().get("Set-Cookie");
//                    //Toast.makeText(context, mysession, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context, MainActivity.class);
//                    intent.putExtra("mycookie",mysession);
//                    context.startActivity(intent);
//                }
//
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.d(TAG, t.getLocalizedMessage());
//
//
////                returnresponses = t.getLocalizedMessage().toString();
//                }
//            });


    }
}

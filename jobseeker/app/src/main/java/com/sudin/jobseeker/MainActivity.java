package com.sudin.jobseeker;


import android.content.Intent;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bgone;
    Button btnget;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgone = (ImageView) findViewById(R.id.bgone);
        btnget = (Button) findViewById(R.id.btnget);

        vibrator =(Vibrator) getSystemService(VIBRATOR_SERVICE);
        bgone.animate().scaleX(2).scaleY(2).setDuration(5000).start();

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(a);
            }
        });

    }
}

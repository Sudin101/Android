package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import com.sudin.jobseeker.Adapter.ItemAdapter;
import com.sudin.jobseeker.Interface.jobpostinterface;
import com.sudin.jobseeker.models.ltems;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showjobpost extends AppCompatActivity {
    String cookie;
    Intent intent;
    Button btn_addjob;
    RecyclerView recyclerView;
    ArrayList<ltems> jobdata = new ArrayList<ltems>();
    com.sudin.jobseeker.Adapter.ItemAdapter ItemAdapter;
    private static final String TAG = ltems.class.getSimpleName();
    String BASE_URL = "http://192.168.1.67:3000/";
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showjobpost);
        intent= getIntent();

        cookie = intent.getStringExtra("cookie");
        Toast.makeText(this, "cookie ho hamro ki nai "+ cookie, Toast.LENGTH_SHORT).show();

        getalldata();

//        recyclerView.setAdapter(postadapter);
//
//        postadapter.notifyDataSetChanged();

    }



    public void populateRecyclerView(ArrayList<com.sudin.jobseeker.models.ltems> postme) {

        recyclerView = (RecyclerView) findViewById(R.id.postkolagirecycleviews);
        ItemAdapter = new ItemAdapter(this, jobdata,cookie);
        recyclerView.setAdapter(ItemAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ArrayList<Post> postarray = new ArrayList<Post>(postme);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//        postadapter = new Postadapter(this, postdata);
//        recyclerView.setAdapter(postadapter);
//
//        postadapter.notifyDataSetChanged();
//
//
//        postadapter = new Postadapter(this, postme);
//        recyclerView.setAdapter(postadapter);
//
//        postadapter.notifyDataSetChanged();

    }

    public ArrayList<ltems> getalldata() {


        jobpostinterface jobpost_interface = retrofit.create(jobpostinterface.class);


        Call<ArrayList<ltems>> call = jobpost_interface.getalldatass(cookie);

        call.enqueue(new Callback<ArrayList<com.sudin.jobseeker.models.ltems>>() {
            @Override
            public void onResponse(Call<ArrayList<com.sudin.jobseeker.models.ltems>> call, Response<ArrayList<com.sudin.jobseeker.models.ltems>> response) {

                if(response.isSuccessful()){
                    jobdata=response.body();
                    populateRecyclerView(response.body());


                    Log.d(TAG,response.body().toString());

//                        populateRecyclerView(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<com.sudin.jobseeker.models.ltems>> call, Throwable t) {
      }
        });

        return null;


    }


}

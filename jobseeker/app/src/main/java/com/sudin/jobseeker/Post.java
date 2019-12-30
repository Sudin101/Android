package com.sudin.jobseeker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sudin.jobseeker.Interface.Authentication;
import com.sudin.jobseeker.Interface.PostInterface;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post extends AppCompatActivity {


    RecyclerView recyclerView;
    String cookie;
    Intent intent;
    ArrayList<com.sudin.jobseeker.models.Post> postdata = new ArrayList<com.sudin.jobseeker.models.Post>();
    private static final String TAG = Post.class.getSimpleName();
    EditText postmatra;
    Button btn_addpost;

    Postadapter postadapter;

    Authentication authentication;

    PostInterface PostInterface;

    //    cookie=



    String BASE_URL = "http://192.168.1.67:3000/";
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        intent= getIntent();

        cookie = intent.getStringExtra("cookie");


        Toast.makeText(this, "post ma tost  "+ cookie, Toast.LENGTH_SHORT).show();


        getAllPostData();

        Toast.makeText(this, cookie, Toast.LENGTH_SHORT).show();
        postmatra = findViewById(R.id.postmatra);
        btn_addpost = findViewById(R.id.btn_addpost);

        btn_addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
//        recyclerView.setAdapter(postadapter);
//
//        postadapter.notifyDataSetChanged();

    }
    private void createInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostInterface = retrofit.create(PostInterface.class);
    }

    private void addItem() {
        createInstance();

        String itemNameFinal = postmatra.getText().toString();


        com.sudin.jobseeker.models.Post item = new com.sudin.jobseeker.models.Post(itemNameFinal);

        Call<Void> voidCall = PostInterface.addpost(cookie,item);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Post.this, "Item Added Successfully ", Toast.LENGTH_LONG).show();

                if(response.isSuccessful()){
                    startActivity(getIntent());
                    Toast.makeText(Post.this, "Sucessfull vayo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Post.this, "Error :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void populateRecyclerView(ArrayList<com.sudin.jobseeker.models.Post> postme) {


        recyclerView = (RecyclerView) findViewById(R.id.postkolagirecycleview);
        postadapter = new Postadapter(this, postdata,cookie);
        recyclerView.setAdapter(postadapter);

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

    public ArrayList<Post> getAllPostData() {


        PostInterface post_Interface_interface = retrofit.create(PostInterface.class);


        Call<ArrayList<com.sudin.jobseeker.models.Post>> call = post_Interface_interface.getalldata(cookie);

        call.enqueue(new Callback<ArrayList<com.sudin.jobseeker.models.Post>>() {
            @Override
            public void onResponse(Call<ArrayList<com.sudin.jobseeker.models.Post>> call, Response<ArrayList<com.sudin.jobseeker.models.Post>> response) {

                if(response.isSuccessful()){
                    postdata=response.body();
                    populateRecyclerView(response.body());


                    Log.d(TAG,response.body().toString());

                    Toast.makeText(Post.this, "Vayo Esko", Toast.LENGTH_SHORT).show();


//                        populateRecyclerView(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<com.sudin.jobseeker.models.Post>> call, Throwable t) {


                Toast.makeText(Post.this, "Vayena Esko", Toast.LENGTH_SHORT).show();


            }
        });


        return null;


    }


}

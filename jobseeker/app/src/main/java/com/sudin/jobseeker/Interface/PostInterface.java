package com.sudin.jobseeker.Interface;

import com.sudin.jobseeker.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PostInterface {

    @GET("post")
    Call<ArrayList<Post>> getalldata(@Header("Cookie") String Cookie);

    @POST("post")
    Call<Void> addpost(@Header("Cookie") String Cookie,@Body Post Body);
}

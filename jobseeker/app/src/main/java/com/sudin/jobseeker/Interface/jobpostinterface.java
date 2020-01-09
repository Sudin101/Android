package com.sudin.jobseeker.Interface;

import com.sudin.jobseeker.models.ltems;
import com.sudin.jobseeker.models.Image;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface jobpostinterface {
    @Multipart
    @POST("uploads")
    Call<Image> uploadImage(@Part MultipartBody.Part body);

    @POST("jobposts")
    Call<Void> addItem(@Header("Cookie") String Cookie,@Body ltems Body);

    @PUT("jobposts/{jobpostid}")
    Call<Void> updateItem(@Header("Cookie") String Cookie,@Body ltems Body,@Part("jobpostid") String jobpostid);


//    @GET("jobpostsfinal")
//    Call<List<ltems>> getAllItems(@Header("Cookie") String Cookie);

    @GET("jobposts/findmyonlydata")
    Call<ArrayList<ltems>> getalldata(@Header("Cookie") String Cookie);

    @GET("jobposts")
    Call<ArrayList<ltems>> getalldatass(@Header("Cookie") String Cookie);
}

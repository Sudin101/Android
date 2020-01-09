package com.sudin.jobseeker.Interface;

import com.sudin.jobseeker.models.Image;

import com.sudin.jobseeker.models.users;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface usersinterfaces {
    @Multipart
    @POST("uploads")
    Call<Image> uploadImage(@Part MultipartBody.Part body);

    @GET("jobpostsfinal")
    Call<List<users>> getAllItems(@Header("Cookie") String Cookie);

    @PUT("user/updatemydata")
    Call<Void> updateItem(@Header("Cookie") String Cookie,@Body users Body);

}

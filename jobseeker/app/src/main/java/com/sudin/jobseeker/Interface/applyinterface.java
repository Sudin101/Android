package com.sudin.jobseeker.Interface;

import com.sudin.jobseeker.models.Image;
import com.sudin.jobseeker.models.apply;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface applyinterface {

    @Multipart
    @POST("uploads")
    Call<Image> uploadImage(@Part MultipartBody.Part body);

    @POST("apply")
    Call<Void> jobapply(@Header("Cookie") String Cookie, @Body apply Body);
}

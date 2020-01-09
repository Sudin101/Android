package com.sudin.jobseeker.Interface;
import com.sudin.jobseeker.models.RegistrationResponse;
import com.sudin.jobseeker.models.User;
import com.sudin.jobseeker.models.users;
import com.sudin.jobseeker.models.usersignup;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Authentication {

    @POST("users/login")
    Call<ResponseBody> loginuser(@Body User user);

    @POST("users/signup")
    Call<RegistrationResponse> userSignup(@Body users user);

    @POST("users/signup")
    Call<RegistrationResponse> userSignups(@Body usersignup user);

    @GET("user/findmyonlydata")
    Call<users>getuserdata(@Header("Cookie") String Cookie);

}

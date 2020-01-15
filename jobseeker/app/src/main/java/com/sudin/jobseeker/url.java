package com.sudin.jobseeker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url{

    public static final String BASE_URL = "http://192.168.1.67:3000/";

    public static String Cookie ="";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

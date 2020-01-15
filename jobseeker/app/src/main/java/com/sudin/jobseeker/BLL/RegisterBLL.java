package com.sudin.jobseeker.BLL;

import com.sudin.jobseeker.Interface.Authentication;
import com.sudin.jobseeker.Url.Url;
import com.sudin.jobseeker.models.RegistrationResponse;
import com.sudin.jobseeker.models.users;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {
    private String First_name;
    private String Last_name;
    private  String username;
    private  String password;

    boolean isSucess = false;


    public RegisterBLL(String first_name, String last_name, String username, String password) {
        First_name = first_name;
        Last_name = last_name;
        this.username = username;
        this.password = password;
    }

    public boolean registerUser(){


        Authentication userInterface= Url.getInstance().create(Authentication.class);

        Call<RegistrationResponse> userModelCall
                = userInterface.userSignup(new users(First_name,Last_name,username,password));
//        Call<RegistrationResponse> userModelCall=userInterface.userRegistration(new User(firstName,username,email,contact_no,image,password));

        try{
            Response<RegistrationResponse> userModelResponse = userModelCall.execute();
            if (userModelResponse.body().getSuccess()){
//                URLclass.Cookie=userModelResponse.headers().get("Set-Cookie");
                isSucess=true;
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return  isSucess;

    }
}

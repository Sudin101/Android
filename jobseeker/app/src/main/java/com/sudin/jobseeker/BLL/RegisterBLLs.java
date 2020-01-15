package com.sudin.jobseeker.BLL;



import com.sudin.jobseeker.Interface.Authentication;
import com.sudin.jobseeker.Url.Url;
import com.sudin.jobseeker.models.RegistrationResponse;
import com.sudin.jobseeker.models.usersignup;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLLs{
    private String FirstName;
    private String email;
    private  String username;
    private  String password;
    private String publisher;

    boolean isSucess = false;


    public RegisterBLLs(String firstName, String email, String username, String password, String publisher) {
        FirstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.publisher = publisher;
    }

    public boolean registerUser(){


        Authentication userInterface= Url.getInstance().create(Authentication.class);

        Call<RegistrationResponse> userModelCall
                = userInterface.userSignups(new usersignup(username,FirstName,email,password,publisher));
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

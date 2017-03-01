package com.saini.retrofitandroid.interf;

import com.saini.retrofitandroid.model.Student;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by navneet on 4/6/16.
 */
public interface RetrofitInterface {
    /*
     define your all method here
    */

    // used as model in case on return array , note here we returen array of model type pre-filled
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();

    // used as model in case on return one object , note here we returen simple model of model type pre-filled

    @GET("api/RetrofitAndroidObjectResponse")
    Call<Student> getStudentOject();

    // if you want return json used this  , note if you need only send string to server used this.

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> postData(@Field("mobile") String username,
                                @Field("code") String countycode,
                                @Field("password") String password,
                                @Field("deviceToken") String deviceId,
                                @Field("device_type")String devicetype);

    // if you want return json used this  , note if you need to send string and img to server used this.
    // "file" is a key provided by your server side and (; filename="pp.png" ") this is patter to attached img

    @Multipart
    @POST("customerimageupload")
    Call<ResponseBody> updateProfile(@Part("file\"; filename=\"pp.png\" ") RequestBody file,
                                     @Part("token") String token);


}

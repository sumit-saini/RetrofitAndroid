package com.saini.retrofitandroid.activity;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mobulous7 on 22/11/16.
 */

public class RetrofitInit {

    public static Retrofit getRetroObject(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}

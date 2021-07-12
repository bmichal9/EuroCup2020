package com.example.eurocup2020.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //klasa API tutaj podajemy standardowy link do wszytskich metod get i post

    private static final String BASE_URL="https://eurocup.online/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}

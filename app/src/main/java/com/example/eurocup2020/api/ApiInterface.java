package com.example.eurocup2020.api;

import com.example.eurocup2020.model.Grupa;
import com.example.eurocup2020.model.Mecz;
import com.example.eurocup2020.model.NewMecz;
import com.example.eurocup2020.model.Punkty;
import com.example.eurocup2020.model.Znajomi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

        //  tutaj podajemy dane do metody post i get

        @FormUrlEncoded
        @POST("eurosave.php")
        Call<NewMecz> saveMecz(
            @Field("id_user") int id_user,
            @Field("id_meczu") int id_meczu,
            @Field("gosp") String gosp,
            @Field("gosc") String gosc,
            @Field("bet_gosp") int bet_gosp,
            @Field("bet_gosc") int bet_gosc
        );

        @GET("euromecze.php")
        Call<List<Mecz>> getMeczes(@Query("id_user") int id_user);

        @GET("euroznajomi.php")
        Call<List<Znajomi>> getZnajomiList(@Query("id_user") int id_user);

        @GET("europointlist.php")
        Call<List<Punkty>> getPointList(@Query("name") String name);

        @GET("eurowyniki.php")
        Call<List<Grupa>> getGrupaList();
}
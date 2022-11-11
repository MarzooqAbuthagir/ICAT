package com.maria.pastelhub.api;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/";


    @GET("lkg/image")
    Call<Results> getImages();

    @GET("ukg/image")
    Call<Results> getUkgImages();

    @POST("maria/login")
    Call<Login> login(@Body JsonObject jsonObject);

    @POST("maria/user")
    Call<Login> register(@Body JsonObject jsonObject);

    @POST("maria/forget")
    Call<Login> forgot(@Body JsonObject jsonObject);

    @POST("/pay")
    Call<Login> pay();

    @GET("maria/user/{id}")
    Call<Login> getDetails(@Path("id") int id);

    @POST("maria/saveanswer")
    Call<SaveScore> saveScore(@Body JsonObject jsonObject);

    @POST("maria/getanswers")
    Call<GetScore> getScore(@Body JsonObject jsonObject);
}

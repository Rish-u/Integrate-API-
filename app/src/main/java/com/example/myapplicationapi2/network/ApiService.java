package com.example.myapplicationapi2.network;

import com.example.myapplicationapi2.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @POST("photos")
    Call<Photo> createPhoto(@Body Photo photo);
}

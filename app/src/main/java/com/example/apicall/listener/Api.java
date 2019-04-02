package com.example.apicall.listener;

import com.example.apicall.model.UserDetails;
import com.example.apicall.model.UserPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL="https://jsonplaceholder.typicode.com";

    @GET("/users")
    Call<ArrayList<UserDetails>> getUserDetails();

    @GET("/posts")
    Call<ArrayList<UserPost>> getUserPost(@Query("userId") int userId);
}

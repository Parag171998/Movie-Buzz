package com.example.moviebuff.Network;

import com.example.moviebuff.Models.TopRatedMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top_rated?api_key=e16f9ec421f01f05db45a6d069d84d56&language=en-US&page=1")
    Call<TopRatedMovies> getMovies();

}

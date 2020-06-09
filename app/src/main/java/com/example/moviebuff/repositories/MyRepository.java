package com.example.moviebuff.repositories;

import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.load.engine.Resource;
import com.example.moviebuff.MainActivity;
import com.example.moviebuff.Models.TopRatedMovies;
import com.example.moviebuff.Network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRepository {

    private static MyRepository myRepositoryInstance;
    public static MyRepository getInstance()
    {
        if(myRepositoryInstance == null)
            myRepositoryInstance = new MyRepository();

        return myRepositoryInstance;
    }
    
    public MutableLiveData<TopRatedMovies> getTopRatedMovies()
    {
        final MutableLiveData<TopRatedMovies> newdata = new MutableLiveData<>();

        Call<TopRatedMovies> topRatedMoviesCall = ApiClient.getInstance().getApi().getMovies();

        topRatedMoviesCall.enqueue(new Callback<TopRatedMovies>() {
            @Override
            public void onResponse(Call<TopRatedMovies> call, Response<TopRatedMovies> response) {
                newdata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TopRatedMovies> call, Throwable t) {
                MainActivity.errorHandling.setValue(t.getMessage());
            }
        });
        return newdata;
    }

}

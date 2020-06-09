package com.example.moviebuff.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviebuff.Models.TopRatedMovies;
import com.example.moviebuff.repositories.MyRepository;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<TopRatedMovies> topRatedMoviesMutableLiveData = null;

    private MyRepository myRepository;

    public void init()
    {
        if(topRatedMoviesMutableLiveData != null)
            return;

        myRepository = MyRepository.getInstance();
        topRatedMoviesMutableLiveData = myRepository.getTopRatedMovies();
    }

    public void setTopRatedMoviesMutableLiveData(MutableLiveData<TopRatedMovies> topRatedMoviesMutableLiveData)
    {
        this.topRatedMoviesMutableLiveData = topRatedMoviesMutableLiveData;
    }
    public LiveData<TopRatedMovies> getTopRatedMoviesMutableLiveData()
    {
        return this.topRatedMoviesMutableLiveData;
    }
}

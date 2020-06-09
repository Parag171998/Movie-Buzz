package com.example.moviebuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviebuff.Adapters.MoviesAdapter;
import com.example.moviebuff.Models.Result;
import com.example.moviebuff.Models.TopRatedMovies;
import com.example.moviebuff.ViewModel.SearchViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchViewModel searchViewModel;

    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;
    List<Result> resultList;
    Button retry;
    public static MutableLiveData<String> errorHandling;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        retry = findViewById(R.id.retryBtn);

        errorHandling = new MutableLiveData<>();

        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);


        resultList = new ArrayList<>();

        showShimmwe();

        recyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        searchViewModel.init();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchViewModel.getTopRatedMoviesMutableLiveData().observe(MainActivity.this, new Observer<TopRatedMovies>() {
                    @Override
                    public void onChanged(TopRatedMovies topRatedMovies) {
                        if(topRatedMovies != null)
                        {
                            if(topRatedMovies.getResults() != null)
                            {
                                resultList.addAll(topRatedMovies.getResults());
                                moviesAdapter.notifyDataSetChanged();
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        }, 3000);

        errorHandling.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                retry.setVisibility(View.VISIBLE);
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this,MainActivity.class);
             startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showShimmwe() {
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        moviesAdapter = new MoviesAdapter(resultList,this);
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("isFirstTime",false);
    }

}

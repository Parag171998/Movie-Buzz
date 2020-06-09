package com.example.moviebuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView img;
    TextView title,rating, relDate, overView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().hide();
        img = findViewById(R.id.details_image);
        title = findViewById(R.id.details_title);
        rating = findViewById(R.id.details_rating);
        relDate = findViewById(R.id.details_relese_date);
        overView = findViewById(R.id.details_overview);

        Intent intent = getIntent();

        String simgUrl = intent.getStringExtra("ImageUrl");
        String stitle = intent.getStringExtra("title");
        String srelDate = intent.getStringExtra("releseData");
        String srating = intent.getStringExtra("rating");
        String soverview = intent.getStringExtra("overView");

        Glide.with(this).load(simgUrl).into(img);
        title.setText("Title : "+stitle);
        rating.setText("Rating : "+srating);
        relDate.setText("Release Date : "+srelDate);
        overView.setText("Overview : "+soverview);
    }
}

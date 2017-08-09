package com.example.android.tonightmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoviesView extends AppCompatActivity {
    private static final String API_URL="https://api.themoviedb.org/3/movie/popular?api_key=";
    private static final String API_KEY="241e6c09418c067fcbc6d39ea6f33e8a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_view);
    }
}

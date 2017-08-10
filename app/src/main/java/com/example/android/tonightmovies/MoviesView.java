package com.example.android.tonightmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MoviesView extends AppCompatActivity {
    private static final String API_URL="https://api.themoviedb.org/3/discover/movie";

    private static final String API_KEY="?api_key=241e6c09418c067fcbc6d39ea6f33e8a";
    private static final String API_SORT_POPULAR ="&sort_by=popularity.desc";
    private static final String API_SORT_RATED ="&sort_by=vote_average.desc";
    T
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_view);


    }
}

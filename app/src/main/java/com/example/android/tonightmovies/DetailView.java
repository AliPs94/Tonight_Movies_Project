package com.example.android.tonightmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.tonightmovies.utilities.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by mba on 8/12/17.
 */

public class DetailView extends AppCompatActivity {
    private Movie mMovieDetails;
    private TextView mMovieDetailsDisplay;
    private ImageView mPoster;
    private TextView mMovieTitleDisplay;
    private TextView mMovieRatingDisplay;
    private TextView    mReleaseDate;
    String imagebaseUrl = "http://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        mMovieTitleDisplay=(TextView) findViewById(R.id.tv_display_title);
        mMovieDetailsDisplay = (TextView) findViewById(R.id.tv_display_detail);
        mPoster=(ImageView)  findViewById(R.id.Iv_poster);
        mReleaseDate=(TextView) findViewById(R.id.releaseDate);
        mMovieRatingDisplay=(TextView) findViewById(R.id.tv_RatingBar);
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                mMovieDetails = (Movie)intentThatStartedThisActivity.getParcelableExtra("movie");
                mMovieDetailsDisplay.setText(mMovieDetails.detials);
                Picasso.with(this).load(Uri.parse(imagebaseUrl+mMovieDetails.imageUrl)).resize(500,770).into(mPoster);
                mMovieTitleDisplay.setText(mMovieDetails.movieName);
                mMovieRatingDisplay.setText(mMovieDetails.rating+"/10");
                mReleaseDate.setText(mMovieDetails.release_date);

            }
        }
    }

}

package com.example.android.tonightmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.tonightmovies.utilities.Movie;
import com.example.android.tonightmovies.utilities.Network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class MoviesView extends AppCompatActivity implements ImageAdapter.ImageAdapterOnClickHandler {
    private RecyclerView mRecyclerView;
    private ImageAdapter mImageAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private String mSort;
    public  Movie [] mMovieArray;

    Context context;

    private static final String API_SORT_POPULAR ="movie/popular";
    private static final String API_SORT_RATED ="movie/top_rated";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_images);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mSort=API_SORT_POPULAR;

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mImageAdapter = new ImageAdapter(this);
        mRecyclerView.setAdapter(mImageAdapter);

        context = this;
        loadMoviesPoster();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.setting, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mi_popular) {
            mSort=API_SORT_POPULAR;
            loadMoviesPoster();
            return true;
        }
        if (id == R.id.mi_topRated) {
            mSort=API_SORT_RATED;
            loadMoviesPoster();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie M) {
        Context context = this;
        Class destinationClass = DetailView.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie",M);

        startActivity(intentToStartDetailActivity);

    }
    private void showMoviePosters() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }



    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {


            URL requestUrl = Network.buildUrl(params[0],context);

            try {
                String jsonResponse = Network.getResponseFromHttpUrl(requestUrl);
                JSONObject MovieJson = new JSONObject(jsonResponse);
                JSONArray movieArray =MovieJson.getJSONArray("results");
                mMovieArray =new Movie[movieArray.length()];
                for(int i = 0; i < movieArray.length(); i++){
                    JSONObject object = movieArray.getJSONObject(i);
                    mMovieArray[i] = new Movie();
                    mMovieArray[i].movieName = object.getString("original_title");
                    mMovieArray[i].imageUrl = object.getString("poster_path");
                    mMovieArray[i].rating=object.getString("vote_average");
                    mMovieArray[i].detials=object.getString("overview");
                    mMovieArray[i].release_date=object.getString("release_date");

                }
                return mMovieArray;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] MovieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (MovieData != null) {
                showMoviePosters();
               mImageAdapter.setMovieData(MovieData);
            } else {
                showErrorMessage();
            }
        }
    }

    private void loadMoviesPoster() {
        showMoviePosters();
       new FetchMoviesTask().execute(mSort);

    }
}

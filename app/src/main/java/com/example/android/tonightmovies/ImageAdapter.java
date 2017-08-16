package com.example.android.tonightmovies;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.tonightmovies.utilities.Movie;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by mba on 8/10/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MoviesPosterAdapterViewHolder>  {

    private Movie [] mMovieData;

    String imagebaseUrl = "http://image.tmdb.org/t/p/w185";

    private final ImageAdapterOnClickHandler mClickHandler;


    public interface ImageAdapterOnClickHandler {
        void onClick(Movie M);
    }
    public ImageAdapter(ImageAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }
    public class MoviesPosterAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMoviePoster;

        public MoviesPosterAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePoster =(ImageView) itemView.findViewById(R.id.Iv_item);
            mMoviePoster.setAdjustViewBounds(true);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie movieDetials = mMovieData[adapterPosition];
            mClickHandler.onClick(movieDetials);
        }

    }
    @Override
    public  MoviesPosterAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.images_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MoviesPosterAdapterViewHolder(view);    }

    @Override
    public void onBindViewHolder(MoviesPosterAdapterViewHolder holder, int position) {
        String imageUrl=imagebaseUrl+mMovieData[position].imageUrl;
        Uri imageUri = Uri.parse(imageUrl);
        Picasso.with(holder.itemView.getContext()).load(imageUri).into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        if (mMovieData != null)
            return mMovieData.length;
        return 0;
    }
    public void setMovieData(Movie[] MovieData) {
        mMovieData = MovieData;
        notifyDataSetChanged();
    }


}
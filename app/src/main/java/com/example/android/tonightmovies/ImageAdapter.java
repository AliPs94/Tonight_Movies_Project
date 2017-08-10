package com.example.android.tonightmovies;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by mba on 8/10/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MoviesPosterAdapterViewHolder>  {
    private String[] mImagesUrl;
    String imagebaseUrl = "image.tmdb.org/t/p/w185/";


    @Override
    public MoviesPosterAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.images_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MoviesPosterAdapterViewHolder(view);    }

    @Override
    public void onBindViewHolder(MoviesPosterAdapterViewHolder holder, int position) {
        String imageUrl=imagebaseUrl+mImagesUrl[position];
        Uri imageUri = Uri.parse(imageUrl);
        holder.mMoviePoster.setImageURI(imageUri);

    }

    @Override
    public int getItemCount() {
       if(mImagesUrl == null) return 0;
        return mImagesUrl.length;
    }
    public void setImagesUrl(String[] ImagesUrl) {
        mImagesUrl = ImagesUrl;
        notifyDataSetChanged();
    }

    public class MoviesPosterAdapterViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mMoviePoster;

        public MoviesPosterAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePoster =(ImageView) itemView.findViewById(R.id.Iv_item);
        }

    }
}
package com.example.android.tonightmovies.utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mba on 8/16/17.
 */

public class Movie implements Parcelable {
    public String imageUrl;
    public String movieName;
    public String rating;
    public  String detials;
    public String release_date;

    public Movie(Parcel in) {
        imageUrl = in.readString();
        movieName = in.readString();
        rating = in.readString();
        detials = in.readString();
        release_date=in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {
        imageUrl = null;
        movieName = null;
        rating = null;
        detials = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(imageUrl);
        parcel.writeString(movieName);
        parcel.writeString(rating);
        parcel.writeString(detials);
        parcel.writeString(release_date);

    }
}

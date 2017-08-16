package com.example.android.tonightmovies.utilities;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.tonightmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by mba on 8/14/17.
 */

public class Network {
    private static final String API_URL="https://api.themoviedb.org/3/";
//    private static final String key=;
    private static final String API_KEY="api_key";
  //  private static final String SORT="sort_by";
//    private static final String API_SORT_POPULAR ==popularity.desc";
//    private static final String API_SORT_RATED ="&sort_by=vote_average.desc";

    public static URL buildUrl(String sortType, Context context) {
        String key = context.getResources().getString(R.string.APIKEY);
        Log.i("f",key);
        String finalUrl=API_URL+sortType;
        Uri builtUri = Uri.parse(finalUrl).buildUpon()
                .appendQueryParameter(API_KEY,key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

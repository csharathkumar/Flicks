package com.codepath.flicks.models;

import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.Log;

import com.codepath.flicks.Utils.Constants;
import com.codepath.flicks.Utils.TagFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharath on 7/23/16.
 */
public class Movie implements Serializable{

    public String getPosterPath(int orientation) {
        String imageUrl = "";
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = TagFormat.from(Constants.IMAGE_URL)
                    .with("IMAGE_SIZE",Constants.BACKDROP_IMAGE_SIZE)
                    .with("POSTER_PATH",backdropPath)
                    .format();
        } else{
            imageUrl = TagFormat.from(Constants.IMAGE_URL)
                    .with("IMAGE_SIZE",Constants.POSTER_IMAGE_SIZE)
                    .with("POSTER_PATH",posterPath)
                    .format();
        }
        Log.d(TAG,"Image Url is -"+imageUrl);
        return imageUrl;
    }
    public String getBackdropPath(){
        return TagFormat.from(Constants.IMAGE_URL)
                .with("IMAGE_SIZE",Constants.BACKDROP_IMAGE_SIZE)
                .with("POSTER_PATH",backdropPath)
                .format();
    }
    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMovieId() {
        return movieId;
    }

    private static final String TAG = Movie.class.getSimpleName();
    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    Double averageRating;
    Double popularity;
    String releaseDate;
    boolean videoAvailable;
    String movieId;



    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.averageRating = jsonObject.getDouble("vote_average");
        this.popularity = jsonObject.getDouble("popularity");
        this.releaseDate = jsonObject.getString("release_date");
        this.videoAvailable = jsonObject.getBoolean("video");
        this.movieId = jsonObject.getString("id");
    }

    public static List<Movie> fromJSONArray(JSONArray jsonArray){
        List<Movie> results = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try{
                results.add(new Movie(jsonArray.getJSONObject(i)));
            }catch(JSONException je){
                Log.e(TAG,"Exception parsing the movie");
            }

        }
        return results;
    }
}

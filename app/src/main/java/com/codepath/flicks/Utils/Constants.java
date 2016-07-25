package com.codepath.flicks.Utils;

/**
 * Created by Sharath on 7/23/16.
 */
public interface Constants {
    String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    String BASE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    String IMAGE_URL = "https://image.tmdb.org/t/p/{IMAGE_SIZE}/{POSTER_PATH}";
    String GET_VIDEOS_URL = "http://api.themoviedb.org/3/movie/{movieId}/videos";
    String POSTER_IMAGE_SIZE = "w342";
    String BACKDROP_IMAGE_SIZE = "w1280";
}

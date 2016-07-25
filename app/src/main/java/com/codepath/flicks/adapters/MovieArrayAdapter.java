package com.codepath.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flicks.R;
import com.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sharath on 7/23/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{
    private int POPULAR_MOVIE_VIEW_TYPE = 1;
    private int MOVIE_VIEW_TYPE = 0;

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,0, movies);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getAverageRating().intValue() > 5)
            return POPULAR_MOVIE_VIEW_TYPE;

        return MOVIE_VIEW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        int rowType = getItemViewType(position);
        //get the data item for position
        Movie movie = getItem(position);
        //check the existing view being reused
        MovieViewHolder movieViewHolder; //view lookup cache stored in tag
        PopularMovieViewHolder popularMovieViewHolder;
        if(convertView == null){
            if(rowType == MOVIE_VIEW_TYPE){
                movieViewHolder = new MovieViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie,parent,false);
                movieViewHolder.ivMoviePoster = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                movieViewHolder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                movieViewHolder.tvOriginalTitle = (TextView) convertView.findViewById(R.id.tvOverview);
                convertView.setTag(movieViewHolder);
            }else if(rowType == POPULAR_MOVIE_VIEW_TYPE){
                popularMovieViewHolder = new PopularMovieViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_popular_movie,parent,false);
                popularMovieViewHolder.ivMovieBackdrop = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                convertView.setTag(popularMovieViewHolder);
            }

        }

        if(rowType == MOVIE_VIEW_TYPE){
            movieViewHolder = (MovieViewHolder) convertView.getTag();
            movieViewHolder.ivMoviePoster.setImageResource(0);
            movieViewHolder.tvMovieTitle.setText(movie.getOriginalTitle());
            movieViewHolder.tvOriginalTitle.setText(movie.getOverview());
            Picasso.with(getContext()).load(movie.getPosterPath(orientation))
                    .placeholder(R.mipmap.ic_image_black)
                    .into(movieViewHolder.ivMoviePoster);
        }else if(rowType == POPULAR_MOVIE_VIEW_TYPE){
            popularMovieViewHolder = (PopularMovieViewHolder) convertView.getTag();
            popularMovieViewHolder.ivMovieBackdrop.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .placeholder(R.mipmap.ic_image_black)
                    .into(popularMovieViewHolder.ivMovieBackdrop);
        }

        return convertView;
    }

    public static class MovieViewHolder{
        ImageView ivMoviePoster;
        TextView tvMovieTitle;
        TextView tvOriginalTitle;
    }
    public static class PopularMovieViewHolder{
        ImageView ivMovieBackdrop;
    }
}

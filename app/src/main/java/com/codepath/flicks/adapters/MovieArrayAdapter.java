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

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int orientation = getContext().getResources().getConfiguration().orientation;

        //get the data item for position
        Movie movie = getItem(position);
        //check the existing view being reused
        MovieViewHolder movieViewHolder; //view lookup cache stored in tag
        if(convertView == null){
            movieViewHolder = new MovieViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            movieViewHolder.ivMoviePoster = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            movieViewHolder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            movieViewHolder.tvOriginalTitle = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(movieViewHolder);
        }else{
            movieViewHolder = (MovieViewHolder) convertView.getTag();
        }
        //clear out image from convertView
        movieViewHolder.ivMoviePoster.setImageResource(0);
        movieViewHolder.tvMovieTitle.setText(movie.getOriginalTitle());
        movieViewHolder.tvOriginalTitle.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath(orientation))
                .placeholder(R.mipmap.ic_image_black)
                .into(movieViewHolder.ivMoviePoster);


        return convertView;
    }

    public static class MovieViewHolder{
        ImageView ivMoviePoster;
        TextView tvMovieTitle;
        TextView tvOriginalTitle;
    }
}

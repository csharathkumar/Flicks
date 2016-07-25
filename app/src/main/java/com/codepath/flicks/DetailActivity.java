package com.codepath.flicks;

import android.databinding.DataBindingUtil;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.flicks.databinding.ActivityDetailBinding;
import com.codepath.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie_details";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);

        Movie movie = null;
        if(getIntent() != null){
            movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
            if(movie != null){
                activityDetailBinding.titleTV.setText(movie.getOriginalTitle());
                activityDetailBinding.releaseDateTV.setText(String.format("%s - %s", getString(R.string.release_date), movie.getReleaseDate()));
                activityDetailBinding.overviewTV.setText(movie.getOverview());
                activityDetailBinding.ratingBar.setRating(movie.getAverageRating().floatValue()/2);
                activityDetailBinding.VideoPreviewPlayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DetailActivity.this,"Play button clicked. Start a youtube video activity",Toast.LENGTH_SHORT).show();
                    }
                });
                Picasso.with(this).load(movie.getBackdropPath())
                        .placeholder(R.mipmap.ic_image_black)
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(activityDetailBinding.backdropIV);
                getSupportActionBar().setTitle(movie.getOriginalTitle());
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.swdave.popular_movies_app_final.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.swdave.popular_movies_app_final.R;
import com.swdave.popular_movies_app_final.activities.DetailActivity;
import com.swdave.popular_movies_app_final.model.MovieResults;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String SMALL_POSTER_URL = "https://image.tmdb.org/t/p/w200";
    private List<MovieResults> results;
    private Context context;


    public MovieAdapter(Context context, List<MovieResults> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        // Loading img from Internet with Glide
        Glide.with(context)
                .load(SMALL_POSTER_URL + results.get(position).getPosterPath())
                .into(holder.thumbImg);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Movie", results.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setFavs(List<MovieResults> results) {
        this.results = results;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbImg;
        RelativeLayout parentLayout;

        MovieViewHolder(View itemView) {
            super(itemView);

            thumbImg = itemView.findViewById(R.id.posterImg);
            parentLayout = itemView.findViewById(R.id.movie_parent_layout);

        }

    }


}

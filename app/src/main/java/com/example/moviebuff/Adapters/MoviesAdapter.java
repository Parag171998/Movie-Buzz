package com.example.moviebuff.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviebuff.Models.Result;
import com.example.moviebuff.MovieDetailsActivity;
import com.example.moviebuff.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    List<Result> resultList;
    Context context;

    public MoviesAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(context).inflate(
                R.layout.custome_recycler_layout,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+resultList.get(position).getPosterPath()).into(holder.image);
        holder.title.setText(resultList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.custome_image);
            title = itemView.findViewById(R.id.custome_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetailsActivity.class);
                    intent.putExtra("ImageUrl","https://image.tmdb.org/t/p/w500"+resultList.get(getAdapterPosition()).getPosterPath());
                    intent.putExtra("title",resultList.get(getAdapterPosition()).getTitle());
                    intent.putExtra("rating",resultList.get(getAdapterPosition()).getVoteAverage());
                    intent.putExtra("releseData",resultList.get(getAdapterPosition()).getReleaseDate());
                    intent.putExtra("overView",resultList.get(getAdapterPosition()).getOverview());
                    context.startActivity(intent);
                }
            });
        }
    }
}

package com.example.cs310_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportPlaceAdapter extends RecyclerView.Adapter<SportPlaceAdapter.ViewHolder> {
    private Context context;

    private List<SportPlace> sportPlaces;

    public SportPlaceAdapter(List<SportPlace> sportPlaces) {
        this.sportPlaces = sportPlaces;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sport_place, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportPlace sportPlace = sportPlaces.get(position);
        SportType sportType = sportPlace.getSportType();
        String dist= sportPlace.getDistance();
        Log.d("SportPlaceAdapter", "Score for " + sportPlace.getName() + ": " + sportPlace.getScore());
       // holder.textViewEmoji.setTextColor(sportType.getColor());

        double score =sportPlace.getScore();

        String score_formatted = String.format("%.1f", score);

        score_formatted+="/5";

        holder.textViewName.setText(sportPlace.getName());
        holder.textViewScore.setText(score_formatted);
        holder.textViewEmoji.setText(sportType.getEmoji());
        holder.textDistance.setText(dist);

        holder.itemView.setOnClickListener(v -> {
            Intent detailIntent = new Intent(context, SportPlaceDetailActivity.class);
            detailIntent.putExtra("IMAGE", sportPlace.getImage());
            detailIntent.putExtra("ID", sportPlace.getId());
            detailIntent.putExtra("NAME", sportPlace.getName());
            detailIntent.putExtra("DESCRIPTION", sportPlace.getDescription());
            context.startActivity(detailIntent);
        });
    }


    @Override
    public int getItemCount() {
        return sportPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEmoji, textViewName, textViewScore, textDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEmoji = itemView.findViewById(R.id.emojiImageView);
            textViewName = itemView.findViewById(R.id.textViewSportPlaceName);
            textViewScore = itemView.findViewById(R.id.textViewScore);
            textDistance=itemView.findViewById(R.id.textDistance);
        }
    }
    public void updateSportPlaces(List<SportPlace> newSportPlaces) {
        sportPlaces.clear();
        sportPlaces.addAll(newSportPlaces);
        notifyDataSetChanged();
    }
}

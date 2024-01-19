package com.example.musemad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// MuseumAdapter.java
public class MuseumAdapter extends RecyclerView.Adapter<MuseumAdapter.ViewHolder> {
    private List<Museum> museums;

    public MuseumAdapter(List<Museum> museums) {
        this.museums = museums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_museum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Museum museum = museums.get(position);

        holder.museumName.setText(museum.getName());
        holder.museumImage.setImageResource(museum.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return museums.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView museumImage;
        public TextView museumName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            museumImage = itemView.findViewById(R.id.museumImage);
            museumName = itemView.findViewById(R.id.museumName);
        }
    }
}

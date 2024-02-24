package com.adga.musemad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MuseumAdapter extends RecyclerView.Adapter<MuseumAdapter.ViewHolder> {
    private List<Museum> museums;
    private OnItemClickListener listener;

    public MuseumAdapter(List<Museum> museums, OnItemClickListener listener) {
        this.museums = museums;
        this.listener = listener;
    }

    public void updateList(List<Museum> newMuseums) {
        // Actualiza la lista de museos del adaptador
        this.museums = newMuseums;

        // Notifica al adaptador que los datos han cambiado
        notifyDataSetChanged();
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
        Glide.with(holder.itemView.getContext())
                .load(museum.getImageUrl())
                .placeholder(R.drawable.prado)
                .into(holder.museumImage);

        // Agregar clic listener a la tarjeta
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(museum);
                }
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(Museum museum);
    }
}

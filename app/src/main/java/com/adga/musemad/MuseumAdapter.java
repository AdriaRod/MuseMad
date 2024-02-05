package com.adga.musemad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.adga.musemad.fragments.DetailFragment;

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
        final Museum museum = museums.get(position);

        holder.museumName.setText(museum.getName());
        holder.museumImage.setImageResource(museum.getImageResourceId());

        // Agrega un OnClickListener al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre el nuevo fragmento y pasa los datos del museo seleccionado
                DetailFragment detailFragment = DetailFragment.newInstance(museum.getName(), museum.getImageResourceId());

                // Inicia la transacción de fragmento
                FragmentTransaction transaction = ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

                // Reemplaza el contenido del contenedor del fragmento con el DetailFragment
                transaction.replace(R.id.fragmentContainer, detailFragment);

                // Añade la transacción al back stack
                transaction.addToBackStack(null);

                // Ejecuta la transacción
                transaction.commit();
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
}

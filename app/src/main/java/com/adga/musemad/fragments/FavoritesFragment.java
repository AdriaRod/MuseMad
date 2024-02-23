package com.adga.musemad.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adga.musemad.FavoritesAdapter;
import com.adga.musemad.Museum;
import com.adga.musemad.MuseumDetail;
import com.adga.musemad.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesAdapter museumAdapter;
    private RecyclerView favrv;

    private List<Museum> museumsfav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favrv = view.findViewById(R.id.recycler_view_favoritos);
        favrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        museumsfav = new ArrayList<>();
        museumsfav.add(new Museum("Museo del Prado", R.drawable.prado,
                getString(R.string.descPrado), true));
        museumsfav.add(new Museum("Museo Thyssen", R.drawable.thyssen,
                getString(R.string.descPrado), false));
        museumsfav.add(new Museum("Museo Reina Sofía", R.drawable.reinasofia,
                getString(R.string.descPrado), true));

        updateFavorites();

        return view;
    }

    private void updateFavorites() {
        // Filtrar los museos para mostrar solo los favoritos (aquellos con el último atributo true)
        List<Museum> favoritos = new ArrayList<>();
        for (Museum museum : museumsfav) {
            if (museum.isFavorite()) {
                favoritos.add(museum);
            }
        }

        museumAdapter = new FavoritesAdapter(favoritos,
                new FavoritesAdapter.OnItemClickListener() {
                    public void onItemClick(Museum museum) {
                        // Abre la actividad de detalles del museo cuando se hace clic en una tarjeta
                        Intent intent = new Intent(getActivity(), MuseumDetail.class);
                        intent.putExtra("museum_name", museum.getName());
                        intent.putExtra("museum_image", museum.getImageResourceId());
                        intent.putExtra("museum_description", museum.getDescription());
                        startActivityForResult(intent, 1); // Iniciar actividad con requestCode 1
                    }
                });

        favrv.setAdapter(museumAdapter);
    }

}

package com.adga.musemad.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private ArrayList<Museum> favoriteMuseums = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favrv = view.findViewById(R.id.recycler_view_favoritos);
        favrv.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        // Obtén las SharedPreferences
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Recupera los datos del museo de SharedPreferences
        int id = sharedPref.getInt("museum_id", 0);
        String name = sharedPref.getString("museum_name", "");
        String imageUrl = sharedPref.getString("museum_image_url", "");
        String description = sharedPref.getString("museum_description", "");
        boolean isFavorite = sharedPref.getBoolean("museum_fav", false);
        int icon = sharedPref.getInt("museum_icon", 0);


        // Verifica si el museo ya está en la lista de favoritos
        Museum existingMuseum = null;
        for (Museum museum : favoriteMuseums) {
            if (museum.getName().equals(name)){
                existingMuseum = museum;
                break;
            }
        }
        // Si el museo ya existe, elimínalo de la lista
        if (existingMuseum != null) {
            favoriteMuseums.remove(existingMuseum);
        } else {
            // Crea un nuevo objeto Museum y agrégalo a la lista de favoritos solo si no existía
            Museum museum = new Museum(id, name, imageUrl, description, isFavorite, 40.4167,
                    -3.6949,icon);
            favoriteMuseums.add(museum);
        }
        updateFavorites();
    }



    private void updateFavorites() {
        museumAdapter = new FavoritesAdapter(favoriteMuseums,
                new FavoritesAdapter.OnItemClickListener() {
                    public void onItemClick(Museum museum) {
                        // Abre la actividad de detalles del museo cuando se hace clic en una tarjeta
                        Intent intent = new Intent(getActivity(), MuseumDetail.class);
                        intent.putExtra("museum_name", museum.getName());
                        intent.putExtra("museum_image_url", museum.getImageUrl());
                        intent.putExtra("museum_description", museum.getDescription());
                        startActivityForResult(intent, 1); // Iniciar actividad con requestCode 1
                    }
                });

        favrv.setAdapter(museumAdapter);
    }

}


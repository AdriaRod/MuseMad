package com.adga.musemad.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adga.musemad.Museum;
import com.adga.musemad.MuseumAdapter;
import com.adga.musemad.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private MuseumAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Inicializar RecyclerView y su adaptador
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_favoritos);
        adapter = new MuseumAdapter(new ArrayList<Museum>(), null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Cargar datos de museos favoritos desde SharedPreferences
        loadFavoriteMuseums();

        return rootView;
    }

    private void loadFavoriteMuseums() {
        // Obtener los datos de los museos favoritos desde SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        String museumName = sharedPreferences.getString("nombreMuseo", "");
        int museumImageResource = sharedPreferences.getInt("imagenMuseo", 0);

        // Crear un objeto Museum a partir de los datos recuperados de SharedPreferences
        Museum museumFavorito = new Museum(museumName, museumImageResource, null);

        // Agregar el museo favorito a la lista
        List<Museum> museums = new ArrayList<>();
        museums.add(museumFavorito);

        // Actualizar los datos en el adaptador y notificar los cambios
        adapter.updateData(museums);
    }
}

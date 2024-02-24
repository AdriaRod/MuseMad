package com.adga.musemad.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adga.musemad.Museum;
import com.adga.musemad.MuseumAdapter;
import com.adga.musemad.MuseumDetail;
import com.adga.musemad.R;
import com.adga.musemad.Settings;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MuseumAdapter museumAdapter;
    private SearchView sView;
    private List<Museum> museums; // Lista de museos original
    private String currentQuery = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sView = view.findViewById(R.id.searchView);

        museums = new ArrayList<>();
        museums.add(new Museum(0,"Museo del Prado", "https://www.hotelindiana" +
                ".es/wp-content/uploads/2017/12/Visitar-Museo-del-Prado-Gratis.jpg", getString(R.string.descPrado), true, 40.4139, -3.6923));
        museums.add(new Museum(1,"Museo Thyssen", "https://blog.arzuaga" +
                ".es/wp-content/uploads/2020/04/museo-thyssen.jpg",
                getString(R.string.descThyssen), false, 40.4167, -3.6949));
        museums.add(new Museum(2,"Museo Reina Sofía", "https://static2.museoreinasofia" +
                ".es/sites/default/files/snippet_museo_sede_principal_5.png",
                getString(R.string.descReinaSofia), true, 40.4167, -3.6949));



        museumAdapter = new MuseumAdapter(museums, new MuseumAdapter.OnItemClickListener() {
            public void onItemClick(Museum museum) {
                // Abre la actividad de detalles del museo cuando se hace clic en una tarjeta
                Intent intent = new Intent(getActivity(), MuseumDetail.class);
                intent.putExtra("museum_id", museum.getId()); // Pasar el ID del museo en lugar de la lista completa
                intent.putExtra("museum_name", museum.getName());
                intent.putExtra("museum_image_url", museum.getImageUrl());
                intent.putExtra("museum_description", museum.getDescription());
                intent.putExtra("museum_fav", museum.isFavorite());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(museumAdapter);

        sView.setIconifiedByDefault(false);

        sView.setQueryHint(getString(R.string.hint_busqueda_museo));

        int id = sView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = sView.findViewById(id);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.TRANSPARENT);
        }

        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentQuery = newText;
                List<Museum> filteredList = filter(museums, newText);

                museumAdapter.updateList(filteredList);

                return true;
            }
        });

        return view;
    }
    private void cambiarEstadoFavoritoMuseo(int museumId) {
        for (Museum museum : museums) {
            if (museum.getId() == museumId) {
                museum.setFavorite(!museum.isFavorite());

                // Actualizar SharedPreferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("museum_fav_" + museum.getId(), museum.isFavorite());
                editor.apply();

                break;
            }
        }
        museumAdapter.notifyDataSetChanged();
    }

    private List<Museum> filter(List<Museum> museums, String query) {
        query = query.toLowerCase().trim();
        List<Museum> filteredList = new ArrayList<>();
        for (Museum museum : museums) {

            if (museum.getName().toLowerCase().contains(query)) {
                filteredList.add(museum);
            }
        }
        return filteredList;
    }
}

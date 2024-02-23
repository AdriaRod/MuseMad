package com.adga.musemad.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
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

        // Inicializa la lista de museos
        List<Museum> museums = new ArrayList<>();
        museums.add(new Museum("Museo del Prado", R.drawable.prado, getString(R.string.descPrado), false));
        museums.add(new Museum("Museo Thyssen", R.drawable.thyssen, getString(R.string.descPrado), false));
        museums.add(new Museum("Museo Reina Sofía", R.drawable.reinasofia,
                getString(R.string.descPrado), false));

        // Configura el adaptador con la lista de museos y un listener de clics
        museumAdapter = new MuseumAdapter(museums, new MuseumAdapter.OnItemClickListener() {
            public void onItemClick(Museum museum) {
                // Abre la actividad de detalles del museo cuando se hace clic en una tarjeta
                Intent intent = new Intent(getActivity(), MuseumDetail.class);
                intent.putExtra("museum_name", museum.getName());
                intent.putExtra("museum_image", museum.getImageResourceId());
                intent.putExtra("museum_description", museum.getDescription());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(museumAdapter);

        sView.setIconifiedByDefault(false);
        sView.setQueryHint("Busca tu museo favorito...");

        // Accede a la vista interna del SearchView y cambia su fondo a transparente
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
                return false;
            }
        });

        return view;
    }
}

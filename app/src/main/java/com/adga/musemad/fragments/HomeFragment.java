package com.adga.musemad.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;

import com.adga.musemad.Museum;
import com.adga.musemad.MuseumAdapter;
import com.adga.musemad.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private MuseumAdapter museumAdapter;
    private SearchView sView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sView = view.findViewById(R.id.searchView);

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



        // Aquí debes crear una lista de objetos Museum con los datos de tus museos
        List<Museum> museums = new ArrayList<>();
        museums.add(new Museum("Museo del Prado", R.drawable.prado, getString(R.string.descPrado)));
// Añade otros museos con descripciones
        museums.add(new Museum("Museo Thyssen", R.drawable.thyssen, getString(R.string.descPrado)));
        museums.add(new Museum("Museo Reina Sofía", R.drawable.reinasofia, getString(R.string.descPrado)));
        museums.add(new Museum("Museo del Prado", R.drawable.prado, getString(R.string.descPrado)));
        museums.add(new Museum("Museo Thyssen", R.drawable.thyssen, getString(R.string.descPrado)));
        museums.add(new Museum("Museo Reina Sofía", R.drawable.reinasofia, getString(R.string.descPrado)));

        museumAdapter = new MuseumAdapter(museums);
        recyclerView.setAdapter(museumAdapter);

        return view;
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}
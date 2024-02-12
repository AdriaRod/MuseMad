package com.adga.musemad.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.adga.musemad.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapFragment() {
    }


    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);



        // Obtén el SupportMapFragment y configura el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        // Deshabilita el gesto de zoom en el mapa
        mMap.getUiSettings().setZoomGesturesEnabled(false);

        // Coordenadas del centro de Madrid
        LatLng centroMadrid = new LatLng(40.416775, -3.703790);

        // Ajusta las coordenadas para que estén un poco más abajo y menos arriba
        double latitudInferior = centroMadrid.latitude - 0.05; // Ajusta este valor según sea necesario
        double longitudInferior = centroMadrid.longitude - 0.05; // Ajusta este valor según sea necesario
        double latitudSuperior = centroMadrid.latitude + 0.05; // Ajusta este valor según sea necesario
        double longitudSuperior = centroMadrid.longitude + 0.05; // Ajusta este valor según sea necesario

        // Define las coordenadas delimitadoras centradas en el centro de Madrid
        LatLngBounds madridBounds = new LatLngBounds(
                new LatLng(latitudInferior, longitudInferior), // Coordenadas de la esquina inferior izquierda (suroeste)
                new LatLng(latitudSuperior, longitudSuperior)  // Coordenadas de la esquina superior derecha (noreste)
        );
        mMap.setLatLngBoundsForCameraTarget(madridBounds);

        // Define el nivel de zoom deseado (por ejemplo, 15.0f)
        float zoomLevel = 15.0f;
        // Actualiza la cámara con la nueva posición y nivel de zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroMadrid, zoomLevel));
    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
//        txtLatitud.setText(""+latLng.latitude);
//        txtLongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
//        txtLatitud.setText(""+latLng.latitude);
//        txtLongitud.setText(""+latLng.longitude);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}
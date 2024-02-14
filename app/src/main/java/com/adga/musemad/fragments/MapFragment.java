package com.adga.musemad.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.adga.musemad.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private static final int REQUEST_LOCATION_PERMISSION = 1234;
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

    // Declara una lista para almacenar los marcadores
    List<Marker> markerList = new ArrayList<>();

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        // Cargar el estilo personalizado desde el archivo JSON
        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style);

        // Aplicar el estilo personalizado al mapa
        mMap.setMapStyle(styleOptions);

//        // Deshabilita el gesto de zoom en el mapa
//        mMap.getUiSettings().setZoomGesturesEnabled(false);

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


        /* AÑADE LOS MUSEOS */
        addMuseums(mMap);


        // Define el nivel de zoom deseado (por ejemplo, 15.0f)
        float zoomLevel = 15.0f;
        // Actualiza la cámara con la nueva posición y nivel de zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroMadrid, zoomLevel));




        /********* UBICACION DEL USUARIO EN EL MAPA ********/
        // Solicitar permisos de ubicación en tiempo de ejecución
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        } else {
            // Si ya tienes los permisos, obtener la ubicación actual
            getCurrentLocation();
        }
    }

    private void getCurrentLocation() {
        // Verificar permisos de ubicación
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si los permisos no están concedidos, solicítalos
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }

        // Obtener el cliente de ubicación fusionada
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        // Obtener la última ubicación conocida
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.puntorojo); // Reemplaza "blue_dot" con el nombre de tu icono
                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Mi Ubicación").icon(icon));
                        }
                    }
                });




}//fin del onMapReady

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, obtener la ubicación actual
                getCurrentLocation();
            } else {
                // Permiso denegado, mostrar un mensaje o tomar una acción alternativa
            }
        }
    }

    private void addMuseums(GoogleMap mMap) {

        /************ AÑADIR MUSEOS **********/

        // Añadir marcador personalizado para el Museo del Prado
        LatLng pradoLocation = new LatLng(40.4139, -3.6922);
        MarkerOptions pradoMarkerOptions = new MarkerOptions()
                .position(pradoLocation)
                .title("Museo del Prado")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.escultura)); // Asigna el icono personalizado
        Marker pradoMarker = mMap.addMarker(pradoMarkerOptions);
        markerList.add(pradoMarker);

        // Añadir marcador personalizado para el Museo Thyssen-Bornemisza
        LatLng thyssenLocation = new LatLng(40.4167, -3.6949);
        MarkerOptions thyssenMarkerOptions = new MarkerOptions()
                .position(thyssenLocation)
                .title("Museo Thyssen-Bornemisza")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cuadro)); // Asigna el icono personalizado
        Marker thyssenMarker = mMap.addMarker(thyssenMarkerOptions);
        markerList.add(thyssenMarker);

        // Añadir marcador personalizado para el Museo Reina Sofía
        LatLng reinaSofiaLocation = new LatLng(40.4081, -3.6947);
        MarkerOptions reinaSofiaMarkerOptions = new MarkerOptions()
                .position(reinaSofiaLocation)
                .title("Museo Reina Sofía")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.busto)); // Asigna el icono personalizado
        Marker reinaSofiaMarker = mMap.addMarker(reinaSofiaMarkerOptions);
        markerList.add(reinaSofiaMarker);

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
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
import android.widget.ImageView;
import android.widget.TextView;

import com.adga.musemad.Museum;
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
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private List<Museum> museums = new ArrayList<>();

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

        // Inicializa la lista de museos
        initMuseums();
    }

    // Método para inicializar la lista de museos
    private void initMuseums() {
        museums.add(new Museum("Museo del Prado", R.drawable.prado, getString(R.string.descPrado)));
        museums.add(new Museum("Museo Thyssen", R.drawable.thyssen, getString(R.string.descThyssen)));
        museums.add(new Museum("Museo Reina Sofía", R.drawable.reinasofia, getString(R.string.descReinaSofia)));
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

        // Configuración de la cámara para una vista en 3D
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(40.416775, -3.703790))  // Centro de Madrid
                .zoom(15)  // Nivel de zoom
                .tilt(45)  // Inclinación para vista en 3D
                .bearing(90)  // Orientación del mapa
                .build();

        // Mover la cámara a la posición configurada
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


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

        // Define un Listener para los clics en los marcadores

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Verifica si el marcador hace referencia a un museo
                if (marker.getTitle() != null && marker.getTitle().startsWith("Museo")) {
                    // Muestra el Bottom Sheet correspondiente al museo
                    showMuseumBottomSheet(marker);
                    // Devuelve true para indicar que se ha manejado el clic en el marcador
                    return true;
                }
                // Devuelve false para permitir que el mapa maneje el clic en el marcador
                return false;
            }
        });


    }


//    // Método para mostrar el Bottom Sheet del museo
//    private void showMuseumBottomSheet(Marker marker) {
//        // Infla el diseño del Bottom Sheet
//        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
//
//        // Configura el contenido del Bottom Sheet con los detalles del museo
//        TextView museumName = bottomSheetView.findViewById(R.id.museumName);
//        museumName.setText(marker.getTitle());
//
//        // Encuentra el ImageView en el layout
//        ImageView museumImage = bottomSheetView.findViewById(R.id.imagenMuseo);
//
//        // Obtiene el id del museo seleccionado
//        String museumTitle = marker.getTitle();
//        int museumImageId = 0;
//        for (Museum museum : museums) {
//            if (museum.getName().equals(museumTitle)) {
//                museumImageId = museum.getImageResourceId();
//                break;
//            }
//        }
//
//        // Asigna la imagen correspondiente al ImageView
//        if (museumImageId != 0) {
//            museumImage.setImageResource(museumImageId);
//        }
//
//
//        // Crea y muestra el Bottom Sheet con BottomSheetBehavior configurado
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
//        bottomSheetDialog.setContentView(bottomSheetView);
//
//        // Configurar el comportamiento del BottomSheetDialog
//        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); // Expande automáticamente el BottomSheetDialog
//        bottomSheetBehavior.setPeekHeight(0); // No muestra el peek del BottomSheetDialog
//
//        bottomSheetDialog.show();
//    }

    // Método para mostrar el Bottom Sheet del museo
    private void showMuseumBottomSheet(Marker marker) {
        // Infla el diseño del Bottom Sheet grande
        View bottomSheetView = getLayoutInflater().inflate(R.layout.large_bottom_sheet_layout, null);

        // Configura el contenido del Bottom Sheet con los detalles del museo
        TextView museumName = bottomSheetView.findViewById(R.id.museumName);
        museumName.setText(marker.getTitle());

        // Encuentra el ImageView en el layout
        ImageView museumImage = bottomSheetView.findViewById(R.id.imagenMuseo);

        // Obtiene el id del museo seleccionado
        String museumTitle = marker.getTitle();
        int museumImageId = 0;
        for (Museum museum : museums) {
            if (museum.getName().equals(museumTitle)) {
                museumImageId = museum.getImageResourceId();
                break;
            }
        }

        // Asigna la imagen correspondiente al ImageView
        if (museumImageId != 0) {
            museumImage.setImageResource(museumImageId);
        }

        // Crea y muestra el Bottom Sheet con BottomSheetBehavior configurado
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        // Configura el comportamiento del BottomSheetDialog
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); // Expande automáticamente el BottomSheetDialog

        bottomSheetDialog.show();
    }


    private void museums_images(){

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
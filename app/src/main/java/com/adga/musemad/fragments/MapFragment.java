package com.adga.musemad.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.adga.musemad.Museum;
import com.adga.musemad.MuseumDetail;
import com.adga.musemad.R;
import com.bumptech.glide.Glide;
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
        museums.add(new Museum(1,"Museo del Prado", "https://www.hotelindiana" +
                ".es/wp-content/uploads/2017/12/Visitar-Museo-del-Prado-Gratis.jpg",
                getString(R.string.descPrado), true, 40.4139, -3.6923,
                R.mipmap.ic_museo_prado_color));
        museums.add(new Museum(2,"Museo Thyssen-Bornemisza", "https://blog.arzuaga" +
                ".es/wp-content/uploads/2020/04/museo-thyssen.jpg", getString(R.string.descPrado), false, 40.4163, -3.6949, R.mipmap.ic_museo_thyssen));
        museums.add(new Museum(3,"Museo Reina Sofía", "https://static2.museoreinasofia" +
                ".es/sites/default/files/snippet_museo_sede_principal_5.png", getString(R.string.descPrado), false, 40.4086, -3.6948, R.mipmap.ic_museo_reina_sofia));
        museums.add(new Museum(4,"Museo Arqueológico Nacional", "https://madridando" +
                ".com/wp-content/uploads/2018/08/museo-arqueológico-nacional.jpeg", getString(R.string.descArqueologico), false, 40.4247, -3.6881, R.mipmap.ic_museo_arqueologico));
        museums.add(new Museum(5,"Museo Sorolla", "https://offloadmedia.feverup.com/madridsecreto" +
                ".co/wp-content/uploads/2023/07/13123446/shutterstock_1223904955-1-1.jpg", getString(R.string.descSorolla), false, 40.4359, -3.6903,R.mipmap.ic_museo_sorolla));
        museums.add(new Museum(6,"Museo de Historia de Madrid", "https://www.esmadrid" +
                ".com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/Museodehistoria663x335_1409746743.637.jpg?itok=nMIQBgrw", getString(R.string.descHistoriaMadrid), false, 40.4155, -3.7074, R.mipmap.ic_museo_historia_madrid));
        museums.add(new Museum(7,"Museo de América", "https://estaticos.esmadrid" +
                ".com/cdn/farfuture/xpDXcfkMNRvlNeMEJJLki9wNUFyzDJkQzPRlp7R2ow4/mtime:1646729529/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/museo_america_1.jpg?itok=J1PJO9d0", getString(R.string.descAmerica), false, 40.4234, -3.7173, R.mipmap.ic_museo_america));
        museums.add(new Museum(8,"Museo Nacional de Antropología", "https://www.esmadrid" +
                ".com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/MuseodeAntropologia663x335_1409759555.317.jpg?itok=niWifcXn", getString(R.string.descAntropologia), false, 40.4217, -3.6897, R.mipmap.ic_museo_antropologia));
        museums.add(new Museum(9,"Museo Cerralbo", "https://a.travel-assets" +
                ".com/findyours-php/viewfinder/images/res70/349000/349228-Cerralbo-Museum.jpg", getString(R.string.descCerralbo), false, 40.4237, -3.7178, R.mipmap.ic_museo_cerralbo));
        museums.add(new Museum(10,"Museo Naval de Madrid", "https://www.naucher" +
                ".com/wp-content/uploads/2022/04/MN-vista-general-sala-2-b.jpg", getString(R.string.descNaval), false, 40.4150, -3.6938, R.mipmap.ic_museo_naval));
        museums.add(new Museum(11,"Museo de Ciencias Naturales", "https://www.que" +
                ".madrid/wp-content/uploads/2021/06/Madrid-Museo-Nacional-de-Ciencias-Naturales.jpg", getString(R.string.descCienciasNaturales), false, 40.4145, -3.6899, R.mipmap.ic_museo_ciencias_naturales));
        museums.add(new Museum(12,"Museo del Traje", "https://estaticos.esmadrid" +
                ".com/cdn/farfuture/kvJKokxVD1Xx7E2RpzInIBSVbFOT7YS5YkLv4_MdBPo/mtime:1638267451/sites/default/files/recursosturisticos/infoturistica/museo_del_traje_madrid_destinoc_alvaro_lopez_13.jpg", getString(R.string.descTraje), false, 40.4101, -3.6876, R.mipmap.ic_museo_traje));
        museums.add(new Museum(13,"Museo Nacional de Artes Decorativas", "https://aepuma" +
                ".org/wp-content/uploads/2023/10/mnad.jpg", getString(R.string.descArtesDecorativas), false, 40.4142, -3.6883, R.mipmap.ic_museo_artes_decorativas));
        museums.add(new Museum(14,"Museo del Ferrocarril", "https://www.cronicanorte" +
                ".es/wp-content/uploads/2017/03/Museo-del-Ferrocarril-1024.jpg", getString(R.string.descFerrocarril), false, 40.3965, -3.6891, R.mipmap.ic_museo_ferrocarril));
        museums.add(new Museum(15,"Museo de San Isidro", "https://estaticos.esmadrid" +
                ".com/cdn/farfuture/p5fqp2o_RdvEDXDyLNNaQ5mcVcjHTndI7N2FTXcS5e4/mtime:1646729781/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/50586636_23122009141417_adj.jpg?itok=waTFGlrj", getString(R.string.descSanIsidro), false, 40.4125, -3.7063, R.mipmap.ic_museo_san_isidro));
        museums.add(new Museum(16,"Museo ABC", "https://museo.abc.es/wp-content/uploads/2014/06" +
                "/165C1463.jpg", getString(R.string.descABC), false, 40.4263, -3.7023, R.mipmap.ic_museo_abc));
        museums.add(new Museum(17,"Museo Lázaro Galdiano", "https://1.bp.blogspot" +
                ".com/_fAoKZhu2X9A/SDrfIoaigSI/AAAAAAAABMQ/fM80OcTc1p8/s1600/LazaroGaldiano0508+007.jpg", getString(R.string.descLazaroGaldiano), false, 40.4351, -3.6848, R.mipmap.ic_museo_lazaro_galdiano));
        museums.add(new Museum(18,"Museo Nacional del Romanticismo", "https://media.traveler" +
                ".es/photos/613784b3d4923f67e2990838/master/w_1600%2Cc_limit/63638.jpg", getString(R.string.descRomanticismo), false, 40.4231, -3.6997, R.mipmap.ic_museo_romanticismo));
        museums.add(new Museum(19,"Museo Nacional de Arte Romano", "https://turismomerida" +
                ".org/assets/uploads/2017/05/museo-romano02.jpg", getString(R.string.descArteRomano), false, 39.4764, -6.3726, R.mipmap.ic_museo_arte_romano));
        museums.add(new Museum(20,"Museo de la Real Academia de Bellas Artes de San Fernando",
                "https://www.esmadrid.com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/fachada_san_fernando.jpg?itok=SILt1IKD", getString(R.string.descRealAcademia), false, 40.4142, -3.6994, R.mipmap.ic_museo_bellas_artes));
        museums.add(new Museum(21,"Museo de la Ciudad", "https://musicaypitanzas.files.wordpress" +
                ".com/2014/07/ax_11828_29_30enhancer.jpg", getString(R.string.descCiudad), false, 40.4158, -3.7118, R.mipmap.ic_museo_ciudad));
        museums.add(new Museum(22,"Museo del Aire", "https://ejercitodelaire.defensa.gob" +
                ".es/EA/museodelaire/assets/images/carrusel/c08.jpg", getString(R.string.descAire), false, 40.3719, -3.7887, R.mipmap.ic_museo_aire));
        museums.add(new Museum(23,"Museo Geominero", "https://images.ecestaticos" +
                ".com/J9WNuih9L3QoRhyVPWY90TSKlXE=/0x0:1920x1079/1200x900/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F98f%2Febd%2Fa7b%2F98febda7b5729adf4f737a72e2cf02c1.jpg", getString(R.string.descGeominero), false, 40.4372, -3.6893, R.mipmap.ic_museo_geominero));
        museums.add(new Museum(24,"Museo Nacional de Reproducciones Artísticas", "https://dynamic" +
                "-media-cdn.tripadvisor.com/media/photo-o/05/3a/37/84/detalle-planta-alta.jpg?w=1200&h=-1&s=1", getString(R.string.descReproducciones), false, 40.4160, -3.6901, R.mipmap.ic_museo_reproducciones_artisticas));
        museums.add(new Museum(25,"Museo Nacional del Teatro", "https://www.festivaldealmagro" +
                ".com/wp-content/uploads/2019/12/claustro-del-museo-del-teatro-3.jpg", getString(R.string.descTeatro), false, 40.4175, -3.6964, R.mipmap.ic_museo_teatro));
        museums.add(new Museum(26,"Museo de la Biblioteca Nacional de España", "https://upload" +
                ".wikimedia.org/wikipedia/commons/2/27/Biblioteca_Nacional_de_España_%28Madrid%29_09.jpg", getString(R.string.descBibliotecaNacional), false, 40.4237, -3.6882, R.mipmap.ic_museo_biblioteca));
        museums.add(new Museum(27,"Museo de la Real Fábrica de Tapices", "https://www" +
                ".patrimonionacional.es/sites/default/files/styles/full/public/2020-05/02-museo_de_tapices.jpg?itok=6CTK8Stz", getString(R.string.descFabricaTapices), false, 40.4255, -3.7196, R.mipmap.ic_museo_tapices));
        museums.add(new Museum(28,"Museo de Escultura al Aire Libre de la Castellana", "https" +
                "://urbanbeatcontenidos.es/wp-content/uploads/2022/03/museo-leganes-2020-art.jpg", getString(R.string.descEsculturaAireLibre), false, 40.4382, -3.6892, R.mipmap.ic_museo_escultura));
        museums.add(new Museum(29,"Museo de los Bomberos", "https://upload.wikimedia" +
                ".org/wikipedia/commons/thumb/8/88/Zaragoza_-_Museo_Bomberos_-_Camiones_%2802%29.jpg/1200px-Zaragoza_-_Museo_Bomberos_-_Camiones_%2802%29.jpg", getString(R.string.descBomberos), false, 40.4109, -3.6542, R.mipmap.ic_museo_bomberos));
        museums.add(new Museum(30,"Museo de la Farmacia Hispana", "https://www.ucm" +
                ".es/data/cont/docs/1593-2018-05-31-Farmacia%20Hispana-_MG_5698_0185_1024p%20-%2072ppp.jpg", getString(R.string.descFarmaciaHispana), false, 40.4315, -3.7017, R.mipmap.ic_museo_farmacia));
        museums.add(new Museum(31,"Museo del Ratoncito Pérez", "https://hostaloriente" +
                ".es/wp-content/uploads/2018/05/casa_museo-del-ratoncito-perez-hostal-oriente-madrid.jpg", getString(R.string.descRatoncitoPerez), false, 40.4151, -3.7107, R.mipmap.ic_museo_ratoncito_perez));
        museums.add(new Museum(32,"Museo de los Canales de Isabel II", "https://www.telemadrid" +
                ".es/2021/03/27/programas/la-otra-agenda/construccion-Canal-Isabel-II-Madrid_2326577326_19636635_1920x1080.jpg", getString(R.string.descCanalesIsabelII), false, 40.4473, -3.7012, R.mipmap.ic_museo_isabel_ii));
        museums.add(new Museum(33,"Museo de la Evolución Humana", "https://vivecamino" +
                ".com/img/poi/av/museo-de-la-evolucion-humana_3544.jpg", getString(R.string.descEvolucionHumana), false, 40.4264, -3.6925, R.mipmap.ic_museo_evolucion_humana));
        museums.add(new Museum(34,"Museo Fundación Telefónica", "https://www.museosdemedianoche" +
                ".cl/sites/default/files/inscripciones/2018/e_eft_imagen1.jpg", getString(R.string.descFundacionTelefonica), false, 40.4237, -3.6923, R.mipmap.ic_museo_telefonica));
        museums.add(new Museum(35,"Museo del Vino", "https://unbuendiaenmadrid" +
                ".com/wp-content/uploads/2023/02/museo-del-vino-navalcarnero-entrada.jpg", getString(R.string.descVino), false, 40.2892, -3.9229, R.mipmap.ic_museo_vino));
        museums.add(new Museum(36,"Museo del Deporte", "https://museodeldeporte" +
                ".es/wp-content/uploads/2023/12/741cf604-19f4-4d30-95d4-9c8f0633346e.jpg", getString(R.string.descDeporte), false, 40.4509, -3.6852, R.mipmap.ic_museo_deporte));
        museums.add(new Museum(37,"Museo del Objeto", "https://media.timeout" +
                ".com/images/105872181/750/422/image.jpg", getString(R.string.descMODO), false, 40.4235, -3.6953, R.mipmap.ic_museo_objeto));
        museums.add(new Museum(38,"Museo del Automóvil", "https://img.remediosdigitales" +
                ".com/4a03c7/viaje_tiempo_10-780x420/450_1000.jpg", getString(R.string.descAutomovil), false, 40.3678, -3.7275, R.mipmap.ic_museo_automovil));
        museums.add(new Museum(39,"Museo de Cera de Madrid", "https://media.tacdn" +
                ".com/media/attractions-splice-spp-674x446/0b/c3/00/1b.jpg", getString(R.string.descCera), false, 40.4173, -3.6949, R.mipmap.ic_museo_cera));
        museums.add(new Museum(40,"Museo de Arte Contemporáneo del Ministerio de Cultura", "https" +
                "://upload.wikimedia.org/wikipedia/commons/3/35/Sede_M.A.M.JPG", getString(R.string.descArteContemporaneo), false, 40.4243, -3.6965, R.mipmap.ic_museo_arte_contemporaneo));

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
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_punto_rojo); // Reemplaza "blue_dot" con el nombre de tu icono
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

        for (Museum museum : museums) {
            LatLng museumLocation = new LatLng(museum.getLatitude(), museum.getLongitude());
            MarkerOptions museumMarkerOptions = new MarkerOptions()
                    .position(museumLocation)
                    .title(museum.getName())
                    .icon(BitmapDescriptorFactory.fromResource(museum.getIconResource())); // Utiliza el icono personalizado del museo

            Marker museumMarker = mMap.addMarker(museumMarkerOptions);
            markerList.add(museumMarker);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Muestra el bottom_sheet correspondiente al marcador
                showMuseumBottomSheet(marker);
                return true;
            }
        });
    }


    // Método para mostrar el Bottom Sheet del museo
    private void showMuseumBottomSheet(Marker marker) {
        // Infla el diseño del Bottom Sheet grande
        View bottomSheetView = getLayoutInflater().inflate(R.layout.large_bottom_sheet_layout, null);

        // Configura el contenido del Bottom Sheet con los detalles del museo
        TextView museumName = bottomSheetView.findViewById(R.id.museumName);
        museumName.setText(marker.getTitle());

        // Encuentra el ImageView en el layout
        ImageView museumImage = bottomSheetView.findViewById(R.id.imagenMuseo);

        // Obtiene el título del museo seleccionado
        String museumTitle = marker.getTitle();
        String museumImageUrl = null;

        // Busca la URL de la imagen del museo
        for (Museum museum : museums) {
            if (museum.getName().equals(museumTitle)) {
                museumImageUrl = museum.getImageUrl();
                break;
            }
        }

        // Utiliza Glide para cargar la imagen desde la URL en el ImageView
        if (museumImageUrl != null) {
            Glide.with(this)
                    .load(museumImageUrl)
                    .into(museumImage);
        } else {
            museumImage.setImageResource(R.drawable.prado);
        }

        // Encuentra el texto "+info" en el layout
        TextView moreInfoTextView = bottomSheetView.findViewById(R.id.info_tv);

        // Agrega el onClickListener para abrir la actividad de detalles del museo
        moreInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el título del marcador (o cualquier identificador único)
                String museumTitle = marker.getTitle();

                // Abre la actividad correspondiente al museo seleccionado
                openMuseumDetailActivity(museumTitle);
            }
        });

        // Crea y muestra el Bottom Sheet con BottomSheetBehavior configurado
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        // Configura el comportamiento del BottomSheetDialog
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); // Expande automáticamente el BottomSheetDialog

        bottomSheetDialog.show();
    }



    private void openMuseumDetailActivity(String museumTitle) {
        Intent intent = new Intent(getContext(), MuseumDetail.class);

        // Busca el museo correspondiente al título
        Museum selectedMuseum = null;
        for (Museum museum : museums) {
            if (museum.getName().equals(museumTitle)) {
                selectedMuseum = museum;
                break;
            }
        }

        // Si se encontró el museo, pasa la URL de la imagen y la descripción como extras
        if (selectedMuseum != null) {
            intent.putExtra("museum_name", museumTitle);
            intent.putExtra("museum_image_url", selectedMuseum.getImageUrl()); // Aquí pasamos la URL de la imagen en lugar del ID de recurso de imagen
            intent.putExtra("museum_description", selectedMuseum.getDescription());
            startActivity(intent);
        }
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
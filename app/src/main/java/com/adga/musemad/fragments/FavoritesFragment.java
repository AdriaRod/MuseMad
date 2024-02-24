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
        museumsfav.add(new Museum("Museo del Prado", "https://www.hotelindiana.es/wp-content/uploads/2017/12/Visitar-Museo-del-Prado-Gratis.jpg",
                getString(R.string.descPrado), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Thyssen", "https://blog.arzuaga.es/wp-content/uploads/2020/04/museo-thyssen.jpg",
                getString(R.string.descPrado), false, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Reina Sofía", "https://static2.museoreinasofia.es/sites/default/files/snippet_museo_sede_principal_5.png",
                getString(R.string.descPrado), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Arqueológico Nacional", "https://www.mujerescambianlosmuseos.com/wp-content/uploads/2022/06/MAN_2015-Jose-Barea-PM-1024x683.jpg", getString(R.string.descArqueologico), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Sorolla", "https://offloadmedia.feverup.com/madridsecreto.co/wp-content/uploads/2023/07/13123446/shutterstock_1223904955-1-1.jpg", getString(R.string.descSorolla), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de Historia de Madrid", "https://www.esmadrid.com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/Museodehistoria663x335_1409746743.637.jpg?itok=nMIQBgrw", getString(R.string.descHistoriaMadrid), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de América", "https://estaticos.esmadrid.com/cdn/farfuture/xpDXcfkMNRvlNeMEJJLki9wNUFyzDJkQzPRlp7R2ow4/mtime:1646729529/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/museo_america_1.jpg?itok=J1PJO9d0", getString(R.string.descAmerica), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional de Antropología", "https://www.esmadrid.com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/MuseodeAntropologia663x335_1409759555.317.jpg?itok=niWifcXn", getString(R.string.descAntropologia), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Cerralbo", "https://a.travel-assets.com/findyours-php/viewfinder/images/res70/349000/349228-Cerralbo-Museum.jpg", getString(R.string.descCerralbo), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Naval de Madrid", "https://www.naucher.com/wp-content/uploads/2022/04/MN-vista-general-sala-2-b.jpg", getString(R.string.descNaval), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de Ciencias Naturales", "https://www.que.madrid/wp-content/uploads/2021/06/Madrid-Museo-Nacional-de-Ciencias-Naturales.jpg", getString(R.string.descCienciasNaturales), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Traje", "https://estaticos.esmadrid.com/cdn/farfuture/kvJKokxVD1Xx7E2RpzInIBSVbFOT7YS5YkLv4_MdBPo/mtime:1638267451/sites/default/files/recursosturisticos/infoturistica/museo_del_traje_madrid_destinoc_alvaro_lopez_13.jpg", getString(R.string.descTraje), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional de Artes Decorativas", "https://aepuma.org/wp-content/uploads/2023/10/mnad.jpg", getString(R.string.descArtesDecorativas), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Ferrocarril", "https://www.cronicanorte.es/wp-content/uploads/2017/03/Museo-del-Ferrocarril-1024.jpg", getString(R.string.descFerrocarril), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de San Isidro", "https://estaticos.esmadrid.com/cdn/farfuture/p5fqp2o_RdvEDXDyLNNaQ5mcVcjHTndI7N2FTXcS5e4/mtime:1646729781/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/50586636_23122009141417_adj.jpg?itok=waTFGlrj", getString(R.string.descSanIsidro), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo ABC", "https://museo.abc.es/wp-content/uploads/2014/06/165C1463.jpg", getString(R.string.descABC), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Lázaro Galdiano", "https://1.bp.blogspot.com/_fAoKZhu2X9A/SDrfIoaigSI/AAAAAAAABMQ/fM80OcTc1p8/s1600/LazaroGaldiano0508+007.jpg", getString(R.string.descLazaroGaldiano), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional del Romanticismo", "https://media.traveler.es/photos/613784b3d4923f67e2990838/master/w_1600%2Cc_limit/63638.jpg", getString(R.string.descRomanticismo), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional de Arte Romano", "https://turismomerida.org/assets/uploads/2017/05/museo-romano02.jpg", getString(R.string.descArteRomano), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Real Academia de Bellas Artes de San Fernando", "https://www.esmadrid.com/sites/default/files/styles/content_type_full/public/recursosturisticos/infoturistica/fachada_san_fernando.jpg?itok=SILt1IKD", getString(R.string.descRealAcademia), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Ciudad", "https://musicaypitanzas.files.wordpress.com/2014/07/ax_11828_29_30enhancer.jpg", getString(R.string.descCiudad), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Aire", "https://ejercitodelaire.defensa.gob.es/EA/museodelaire/assets/images/carrusel/c08.jpg", getString(R.string.descAire), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Geominero", "https://images.ecestaticos.com/J9WNuih9L3QoRhyVPWY90TSKlXE=/0x0:1920x1079/1200x900/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F98f%2Febd%2Fa7b%2F98febda7b5729adf4f737a72e2cf02c1.jpg", getString(R.string.descGeominero), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional de Reproducciones Artísticas", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/3a/37/84/detalle-planta-alta.jpg?w=1200&h=-1&s=1", getString(R.string.descReproducciones), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Nacional del Teatro", "https://www.festivaldealmagro.com/wp-content/uploads/2019/12/claustro-del-museo-del-teatro-3.jpg", getString(R.string.descTeatro), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Biblioteca Nacional de España", "https://upload.wikimedia.org/wikipedia/commons/2/27/Biblioteca_Nacional_de_España_%28Madrid%29_09.jpg", getString(R.string.descBibliotecaNacional), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Real Fábrica de Tapices", "https://www.patrimonionacional.es/sites/default/files/styles/full/public/2020-05/02-museo_de_tapices.jpg?itok=6CTK8Stz", getString(R.string.descFabricaTapices), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de Escultura al Aire Libre de la Castellana", "https://urbanbeatcontenidos.es/wp-content/uploads/2022/03/museo-leganes-2020-art.jpg", getString(R.string.descEsculturaAireLibre), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de los Bomberos", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Zaragoza_-_Museo_Bomberos_-_Camiones_%2802%29.jpg/1200px-Zaragoza_-_Museo_Bomberos_-_Camiones_%2802%29.jpg", getString(R.string.descBomberos), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Farmacia Hispana", "https://www.ucm.es/data/cont/docs/1593-2018-05-31-Farmacia%20Hispana-_MG_5698_0185_1024p%20-%2072ppp.jpg", getString(R.string.descFarmaciaHispana), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Ratoncito Pérez", "https://hostaloriente.es/wp-content/uploads/2018/05/casa_museo-del-ratoncito-perez-hostal-oriente-madrid.jpg", getString(R.string.descRatoncitoPerez), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de los Canales de Isabel II", "https://www.telemadrid.es/2021/03/27/programas/la-otra-agenda/construccion-Canal-Isabel-II-Madrid_2326577326_19636635_1920x1080.jpg", getString(R.string.descCanalesIsabelII), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de la Evolución Humana", "https://vivecamino.com/img/poi/av/museo-de-la-evolucion-humana_3544.jpg", getString(R.string.descEvolucionHumana), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo Fundación Telefónica", "https://www.museosdemedianoche.cl/sites/default/files/inscripciones/2018/e_eft_imagen1.jpg", getString(R.string.descFundacionTelefonica), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Vino", "https://unbuendiaenmadrid.com/wp-content/uploads/2023/02/museo-del-vino-navalcarnero-entrada.jpg", getString(R.string.descVino), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Deporte", "https://museodeldeporte.es/wp-content/uploads/2023/12/741cf604-19f4-4d30-95d4-9c8f0633346e.jpg", getString(R.string.descDeporte), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Objeto", "https://media.timeout.com/images/105872181/750/422/image.jpg", getString(R.string.descMODO), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo del Automóvil", "https://img.remediosdigitales.com/4a03c7/viaje_tiempo_10-780x420/450_1000.jpg", getString(R.string.descAutomovil), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de Cera de Madrid", "https://media.tacdn.com/media/attractions-splice-spp-674x446/0b/c3/00/1b.jpg", getString(R.string.descCera), true, 40.4167, -3.6949));
        museumsfav.add(new Museum("Museo de Arte Contemporáneo del Ministerio de Cultura", "https://upload.wikimedia.org/wikipedia/commons/3/35/Sede_M.A.M.JPG", getString(R.string.descArteContemporaneo), true, 40.4167, -3.6949));


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
                        intent.putExtra("museum_image_url", museum.getImageUrl());
                        intent.putExtra("museum_description", museum.getDescription());
                        startActivityForResult(intent, 1); // Iniciar actividad con requestCode 1
                    }
                });

        favrv.setAdapter(museumAdapter);
    }

}

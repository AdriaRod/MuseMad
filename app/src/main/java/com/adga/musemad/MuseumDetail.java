package com.adga.musemad;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.adga.musemad.Museum;
import com.adga.musemad.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MuseumDetail extends AppCompatActivity {

    private Museum museum;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener los datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            museum = new Museum(
                    extras.getString("museum_name"),
                    extras.getString("museum_image_url"),
                    extras.getString("museum_description"),
                    false // Por defecto, no es favorito
            );

            // Mostrar los datos en la interfaz de usuario
            TextView nameTextView = findViewById(R.id.detailTitle);
            ImageView imageView = findViewById(R.id.detailImage);
            TextView descriptionTextView = findViewById(R.id.detailDesc);
            FloatingActionButton favoritesButton = findViewById(R.id.botonFav);

            nameTextView.setText(museum.getName());

            // Utilizar Glide para cargar la imagen desde la URL en el ImageView
            Glide.with(this)
                    .load(museum.getImageUrl())
                    .into(imageView);

            descriptionTextView.setText(museum.getDescription());
        }
    }
}

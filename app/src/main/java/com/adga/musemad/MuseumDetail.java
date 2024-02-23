package com.adga.musemad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                    extras.getInt("museum_image"),
                    extras.getString("museum_description"),
                    false // Por defecto, no es favorito
            );

            // Mostrar los datos en la interfaz de usuario
            TextView nameTextView = findViewById(R.id.detailTitle);
            ImageView imageView = findViewById(R.id.detailImage);
            TextView descriptionTextView = findViewById(R.id.detailDesc);
            FloatingActionButton favoritesButton = findViewById(R.id.botonFav);

            nameTextView.setText(museum.getName());
            imageView.setImageResource(museum.getImageResourceId());
            descriptionTextView.setText(museum.getDescription());


        }
    }

}

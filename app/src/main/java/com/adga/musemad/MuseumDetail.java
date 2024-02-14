package com.adga.musemad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adga.musemad.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MuseumDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener los datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String museumName = extras.getString("museum_name");
            final int museumImageResource = extras.getInt("museum_image", 0);
            final String museumDescription = extras.getString("museum_description");

            // Mostrar los datos en la interfaz de usuario
            TextView nameTextView = findViewById(R.id.detailTitle);
            ImageView imageView = findViewById(R.id.detailImage);
            TextView descriptionTextView = findViewById(R.id.detailDesc);
            FloatingActionButton favoritesButton = findViewById(R.id.botonFav);

            nameTextView.setText(museumName);
            imageView.setImageResource(museumImageResource);
            descriptionTextView.setText(museumDescription);

            favoritesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Guardar los datos del museo en SharedPreferences o en tu base de datos local
                    SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreMuseo", museumName);
                    editor.putInt("imagenMuseo", museumImageResource);
                    editor.apply();
                    // Notificar al usuario que el museo ha sido agregado a favoritos
                    Toast.makeText(MuseumDetail.this, "Museo agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

package com.adga.musemad;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.adga.musemad.R;

public class MuseumDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener los datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String museumName = extras.getString("museum_name");
            int museumImageResource = extras.getInt("museum_image", 0);
            String museumDescription = extras.getString("museum_description");

            // Mostrar los datos en la interfaz de usuario
            TextView nameTextView = findViewById(R.id.detailTitle);
            ImageView imageView = findViewById(R.id.detailImage);
            TextView descriptionTextView = findViewById(R.id.detailDesc);

            nameTextView.setText(museumName);
            imageView.setImageResource(museumImageResource);
            descriptionTextView.setText(museumDescription);
        }
    }
}

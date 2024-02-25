package com.adga.musemad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.adga.musemad.R;
import com.adga.musemad.fragments.FavoritesFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MuseumDetail extends AppCompatActivity {
    private Museum museum;
    private SharedPreferences sharedPreferences;
    private FloatingActionButton favoritesButton;

    private boolean dataSent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inicializar SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("TAG", "onCreate: " + dataSent);
        // Obtener los datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener la lista de museos del intent
            museum = new Museum(
                    extras.getInt("museum_id"),
                    extras.getString("museum_name"),
                    extras.getString("museum_image_url"),
                    extras.getString("museum_description"),
                    extras.getBoolean("museum_fav"), 40.4167, -3.6949,
                    extras.getInt("museum_icon"));

            TextView nameTextView = findViewById(R.id.detailTitle);
            ImageView imageView = findViewById(R.id.detailImage);
            TextView descriptionTextView = findViewById(R.id.detailDesc);
            favoritesButton = findViewById(R.id.botonFav);

            Log.d("TAG", "onClick: ADD TO FAVORITES METHOD " + museum.isFavorite());

            if (museum.isFavorite()) {
                favoritesButton.setImageResource(R.drawable.heart2);
            } else {
                favoritesButton.setImageResource(R.drawable.heart);
            }

            nameTextView.setText(museum.getName());

            Glide.with(this)
                    .load(museum.getImageUrl())
                    .into(imageView);

            descriptionTextView.setText(museum.getDescription());

            favoritesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    museum.setFavorite(!museum.isFavorite());

                    dataSent = !dataSent;
                    SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putInt("museum_id", museum.getId());
                    editor.putString("museum_name", museum.getName());
                    editor.putString("museum_image_url", museum.getImageUrl());
                    editor.putString("museum_description", museum.getDescription());
                    editor.putBoolean("museum_fav", museum.isFavorite());
                    editor.putInt("museum_icon", museum.getIconResource());
                    editor.putBoolean("data_sent", dataSent);

                    editor.apply();
                    sendDataToFragment();

                    if (museum.isFavorite()) {
                        favoritesButton.setImageResource(R.drawable.heart2);
                    } else {
                        favoritesButton.setImageResource(R.drawable.heart);
                    }
                }
            });

        }
    }
    public void sendDataToFragment() {
        dataSent = true;
    }
}
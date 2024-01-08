package com.example.musemad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Glide.with(this).load(R.drawable.fondologin)
                .transform(new BlurTransformation(25))
                .into((ImageView) findViewById(R.id.backgroundImage));
    }

    public void openLogin(View v) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}
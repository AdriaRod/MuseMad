package com.example.musemad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.musemad.databinding.ActivityMainBinding;
import com.example.musemad.fragments.HomeFragment;
import com.example.musemad.fragments.MapFragment;
import com.example.musemad.fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());



        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.id_home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.id_map) {
                replaceFragment(new MapFragment());
            } else if (itemId == R.id.id_person) {
                replaceFragment(new ProfileFragment());
            }

            return true;
        });


    }//onCreate fin


    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

}//fin
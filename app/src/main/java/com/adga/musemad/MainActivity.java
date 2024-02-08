package com.adga.musemad;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.adga.musemad.databinding.ActivityMainBinding;
import com.adga.musemad.fragments.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter sectionsPagerAdapter; //1
    private MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //3
        ViewPager viewPager1 = findViewById(R.id.view_pager);
        viewPager1.setAdapter(sectionsPagerAdapter);

        //4
        BottomNavigationView mybottomNavView = findViewById(R.id.bottom_navigation);

        //6
        mybottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.id_home) {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView, itemId);
                    viewPager1.setCurrentItem(0);
                } else if (itemId == R.id.id_mapa) {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "MAP", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView, itemId);
                    viewPager1.setCurrentItem(1);
                } else if (itemId == R.id.id_profile) {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "PROFILE", Toast.LENGTH_SHORT).show();
                    removeBadge(mybottomNavView, itemId);
                    viewPager1.setCurrentItem(2);
                }
                return false;
            }
        });

        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mybottomNavView.getMenu().getItem(0).setChecked(false);
                    mybottomNavView.getMenu().getItem(position).setChecked(true);
                    removeBadge(mybottomNavView, mybottomNavView.getMenu().getItem(position).getItemId());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }//onCreate fin


    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }



}//fin
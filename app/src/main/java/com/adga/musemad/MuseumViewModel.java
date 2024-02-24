package com.adga.musemad;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MuseumViewModel extends ViewModel {
    private List<Museum> favoriteMuseums = new ArrayList<>();

    public void addToFavorites(Museum museum) {
        favoriteMuseums.add(museum);
    }

    public List<Museum> getFavoriteMuseums() {
        return favoriteMuseums;
    }
}

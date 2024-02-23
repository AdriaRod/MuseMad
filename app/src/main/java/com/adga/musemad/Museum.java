package com.adga.musemad;

// Museum.java
public class Museum {
    private String name;
    private String imageUrl; // Cambia el tipo de datos a String para almacenar la URL de la imagen
    private String description;
    private boolean fav;

    public Museum(String name, String imageUrl, String description, boolean fav) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.fav = fav;
    }


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
    public boolean isFavorite() {
        return fav;
    }
    public void setFavorite(boolean favorite) {
        this.fav = favorite;
    }
}


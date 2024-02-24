package com.adga.musemad;

// Museum.java
public class Museum {
    private int id;
    private String name;
    private String imageUrl; // Cambia el tipo de datos a String para almacenar la URL de la imagen
    private String description;
    private boolean fav;
    private double latitude;
    private double longitude;
    private int iconResource;

    public Museum(int id,String name, String imageUrl, String description, boolean fav,
                  double latitude, double longitude, int iconResource) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.fav = fav;
        this.latitude = latitude;
        this.longitude = longitude;
        this.iconResource = iconResource;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getIconResource(){return iconResource;}

    public int getId() {
        return id;
    }
}

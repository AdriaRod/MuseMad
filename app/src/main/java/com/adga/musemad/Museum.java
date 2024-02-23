package com.adga.musemad;

// Museum.java
public class Museum {
    private String name;
    private int imageResourceId;
    private String description;
    private boolean fav;
    public Museum(String name, int imageResourceId, String description, boolean fav) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.description = String.valueOf(description);
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
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


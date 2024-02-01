package com.adga.musemad;

// Museum.java
public class Museum {
    private String name;
    private int imageResourceId;

    public Museum(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

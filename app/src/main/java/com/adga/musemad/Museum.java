package com.adga.musemad;

// Museum.java
public class Museum {
    private String name;
    private int imageResourceId;
    private String description;

    public Museum(String name, int imageResourceId, String description) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.description = String.valueOf(description);
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
}


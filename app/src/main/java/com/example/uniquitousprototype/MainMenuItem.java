package com.example.uniquitousprototype;

import android.media.Image;

/**
 * Created by YUNKYUSEOK on 2017-08-08.
 */

public class MainMenuItem {
    private int image;
    private String name;
    private float rating;
    private int times;

    private String text;
    private int icon;

    public MainMenuItem(int image, String name, int rating, int times) {
        this.image = image;
        this.name = name;
        this.rating = (float) rating / 2;
        this.times = times;
    }

    public MainMenuItem(int icon, String text) {
        this.text = text;
        this.icon = icon;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

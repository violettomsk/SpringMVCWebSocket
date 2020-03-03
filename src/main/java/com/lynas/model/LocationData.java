package com.lynas.model;

public class LocationData {
    private float lat;
    private float lon;

    public LocationData() {
        this.lat = 0;
        this.lon = 0;
    }

    public LocationData(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void setLat(float val) {
        this.lat = val;
    }

    public void setLon(float val) {
        this.lon = val;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}

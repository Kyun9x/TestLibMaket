package com.lib.marketplace.model.orderonline;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by MrOne on 3/13/2017.
 */

public class DmLocation implements Serializable {

    private double latitude;
    private double longitude;
    private String address;
    private float radius = 0;

    public DmLocation(LatLng latLng, String address) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        this.address = address;
    }

    public LatLng getLatLng() {
        LatLng latLng = new LatLng(latitude, longitude);
        return latLng;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatLng(LatLng latLng) {
        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public String getValuesKm() {

        return "" + (int) radius / 1000 + "km";
    }

    @Override
    public String toString() {
        return "DmLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", radius=" + radius +
                '}';
    }
}

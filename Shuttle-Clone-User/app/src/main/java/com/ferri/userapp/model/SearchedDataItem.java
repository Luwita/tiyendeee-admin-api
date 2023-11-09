package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

public class SearchedDataItem {

    @SerializedName("location_address")
    private String locationAddress;

    @SerializedName("city")
    private String city;

    @SerializedName("location_latitude")
    private double locationLatitude;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private String id;

    @SerializedName("state")
    private String state;

    @SerializedName("type")
    private String type;

    @SerializedName("location_longitude")
    private double locationLongitude;

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getCity() {
        return city;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public String getType() {
        return type;
    }

    public SearchedDataItem(String name,String locationAddress,  double locationLatitude, double locationLongitude, String id, String state, String city,String type) {
        this.locationAddress = locationAddress;
        this.city = city;
        this.type = type;
        this.locationLatitude = locationLatitude;
        this.title = name;
        this.id = id;
        this.state = state;
        this.locationLongitude = locationLongitude;
    }
}

package com.ferri.userapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchLocationResponseModel {

    @SerializedName("data")
    private List<SearchedDataItem> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public List<SearchedDataItem> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}


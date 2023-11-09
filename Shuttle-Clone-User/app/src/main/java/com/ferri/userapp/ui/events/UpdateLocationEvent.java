package com.ferri.userapp.ui.events;


import com.ferri.userapp.model.SearchedDataItem;

public class UpdateLocationEvent {
    private SearchedDataItem locationData;
    private String updateFor;

    public UpdateLocationEvent(SearchedDataItem locationData, String updateFor) {
        this.locationData = locationData;
        this.updateFor = updateFor;
    }

    public SearchedDataItem getLocationData() {
        return locationData;
    }

    public void setLocationData(SearchedDataItem locationData) {
        this.locationData = locationData;
    }

    public String getUpdateFor() {
        return updateFor;
    }

    public void setUpdateFor(String updateFor) {
        this.updateFor = updateFor;
    }
}

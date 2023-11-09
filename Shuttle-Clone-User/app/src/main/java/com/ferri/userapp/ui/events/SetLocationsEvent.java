package com.ferri.userapp.ui.events;


import com.ferri.userapp.model.SearchedDataItem;

public class SetLocationsEvent {
    private SearchedDataItem pickUpData,DropData;

    public SetLocationsEvent(SearchedDataItem pickUpData, SearchedDataItem dropData) {
        this.pickUpData = pickUpData;
        DropData = dropData;
    }

    public SearchedDataItem getPickUpData() {
        return pickUpData;
    }

    public void setPickUpData(SearchedDataItem pickUpData) {
        this.pickUpData = pickUpData;
    }

    public SearchedDataItem getDropData() {
        return DropData;
    }

    public void setDropData(SearchedDataItem dropData) {
        DropData = dropData;
    }
}

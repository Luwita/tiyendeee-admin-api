package com.ferri.userapp.ui.events;

public class ApiSearchResultEvents {

    public String setAddressEvent;
    private String name,sub_name,toWhich,setAddressFor;
    private Double lat,lng;
    private Boolean isCalledFromMain;

    public ApiSearchResultEvents(String setAddressEvent, String name, String sub_name, Double lat, Double lng, String toWhich, String setAddressFor) {
        this.setAddressEvent = setAddressEvent;
        this.name = name;
        this.sub_name = sub_name;
        this.toWhich = toWhich;
        this.lat = lat;
        this.lng = lng;
        this.setAddressFor = setAddressFor;
    }

    public String getSetAddressFor() {
        return setAddressFor;
    }

    public void setSetAddressFor(String setAddressFor) {
        this.setAddressFor = setAddressFor;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public Boolean getCalledFromMain() {
        return isCalledFromMain;
    }

    public void setCalledFromMain(Boolean calledFromMain) {
        isCalledFromMain = calledFromMain;
    }

    public String getSetAddressEvent() {
        return setAddressEvent;
    }

    public void setSetAddressEvent(String setAddressEvent) {
        this.setAddressEvent = setAddressEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToWhich() {
        return toWhich;
    }

    public void setToWhich(String toWhich) {
        this.toWhich = toWhich;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}

package com.example.myapplication;

public class Retrieve_bus_data_base {

    private String ADDRESS;
    private String lat;
    private String lon;
    private String price;
    private String busStopName;


    public Retrieve_bus_data_base() {
    }

    public Retrieve_bus_data_base(String ADDRESS, String lat, String lon, String price, String busStopName) {
        this.ADDRESS = ADDRESS;
        this.lat = lat;
        this.lon = lon;
        this.price = price;
        this.busStopName = busStopName;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }
}

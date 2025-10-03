package com.simon.turistguide2.model;

public class City {
    private int cityID;
    private String name;

    public City(int cityID, String name) {
        this.cityID = cityID;
        this.name = name;
    }

    public City() {

    }

    public String getName() {
        return name;
    }

    public int getCityID() {
        return cityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
}

package com.simon.turistguide2.model;


import java.util.List;
import java.util.Objects;

public class TouristAttraction {
    private int attractionID;
    private String name;
    private String description;
    private int cityID;


    public TouristAttraction(int attractionID, String name, String description, int cityID) {
        this.name = name;
        this.description = description;
        this.cityID = cityID;
        this.attractionID = attractionID;
    }

    public TouristAttraction(){
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttractionID(int attractionID) {
        this.attractionID = attractionID;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TouristAttraction that = (TouristAttraction) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(city, that.city) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, cityID);
    }*/
}

package com.simon.turistguide2.model;


public class TouristAttraction {
    private int attractionID;
    private String name;
    private String description;
    private Integer cityID;


    public TouristAttraction(int attractionID, String name, String description, int cityID) {
        this.attractionID = attractionID;
        this.name = name;
        this.description = description;
        this.cityID = cityID;
    }

    public TouristAttraction(){
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCityID() {
        return cityID;
    }

    public int getAttractionID() {
        return attractionID;
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

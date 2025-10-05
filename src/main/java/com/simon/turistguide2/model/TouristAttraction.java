package com.simon.turistguide2.model;


import java.util.List;

public class TouristAttraction {
    private int attractionID;
    private String name;
    private String description;
    private Integer cityID;
    private String cityName;
    private List<Tag> tags;



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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

}

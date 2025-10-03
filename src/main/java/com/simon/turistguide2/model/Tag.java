package com.simon.turistguide2.model;

public class Tag {
    private int tagID;
    private String name;
    private Integer attractionID;

    public Tag(int tagID, String name, int attractionID) {
        this.tagID = tagID;
        this.name = name;
        this.attractionID = attractionID;

    }

    public Tag() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public Integer getAttractionID() {
        return attractionID;
    }

    public void setAttractionID(int attractionID){
        this.attractionID = attractionID;
    }
}
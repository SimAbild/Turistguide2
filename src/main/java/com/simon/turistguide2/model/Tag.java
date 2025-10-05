package com.simon.turistguide2.model;

public class Tag {
    private int tagID;
    private String name;

    public Tag(int tagID, String name) {
        this.tagID = tagID;
        this.name = name;
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

}
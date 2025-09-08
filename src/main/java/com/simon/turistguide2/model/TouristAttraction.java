package com.simon.turistguide2.model;


import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private int entryFee;

    public TouristAttraction(String name, String description, String city, List<String> tags, int entryFee) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.entryFee = entryFee;
    }

    public TouristAttraction(){

    }

    public int getEntryFee(){
        return entryFee;
    }

    public void setEntryFee(int entryFee){
        this.entryFee = entryFee;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

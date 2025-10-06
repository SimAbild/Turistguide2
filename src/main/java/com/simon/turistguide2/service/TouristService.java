package com.simon.turistguide2.service;

import com.simon.turistguide2.model.City;
import com.simon.turistguide2.model.Tag;
import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.repository.TouristRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRespository repository;

    public TouristService(TouristRespository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getTouristAttractions() {
        return repository.getTouristAttractions();
    }

    public List<City> getAllCities(){
        return repository.getAllCities();
    }

    public List<Tag> getAllTags(){
        return repository.getAllTags();
    }

    public List<Tag> getTagsForAttraction(int attractionID) {
        return repository.getTagsForAttraction(attractionID);
    }

    public TouristAttraction findAttractionByID(int attractionID){
        return repository.findAttractionByID(attractionID);
    }

    public TouristAttraction addAttraction(String name, String description, int cityID) {
        return repository.addAttraction(name, description, cityID);
    }

    public void addTagsToAttraction(int attractionID, List<Integer> tagIDs) {
        repository.addTagsToAttraction(attractionID, tagIDs);
    }

    public List<Integer> getTagIDsForAttraction(int attractionID) {
        return repository.getTagIDsForAttraction(attractionID);
    }

    public void updateAttraction(TouristAttraction attraction, List<Integer> tagIDs) {
        repository.updateAttraction(attraction);                // Opdater attraction info
        repository.updateAttractionTags(attraction.getAttractionID(), tagIDs); // Opdater tags
    }

    public void deleteAttractionByID(int id){
        repository.deleteAttractionByID(id);
    }
}
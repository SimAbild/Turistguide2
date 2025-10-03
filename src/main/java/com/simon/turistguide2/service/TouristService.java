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

    public List<TouristAttraction> findAttractionByName(int attractionID){
       return repository.findAttractionByID(attractionID);
    }

    public TouristAttraction addAttraction(String name, String description, int cityID) {
        return repository.addAttraction(name, description, cityID);
    }

    /*
    public List<Integer> addTags(List<Integer> tagIDs, TouristAttraction touristAttraction) {
        return tagIDs;
    } */
/*
   public TouristAttraction deleteAttraction(String name){
        return repository.deleteAttraction(name);
    }

    public TouristAttraction updateAttraction(TouristAttraction touristAttraction) {
        return repository.updateAttraction(touristAttraction);
    }
*/
}

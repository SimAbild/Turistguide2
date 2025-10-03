package com.simon.turistguide2.service;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.repository.TouristRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<TouristAttraction> findAttractionByName(int attractionID){
       return repository.findAttractionByID(attractionID);
    }

    public TouristAttraction addAttraction(String name, int cityID, String description) {
        return repository.addAttraction(name, cityID, description);
    }
/*
   public TouristAttraction deleteAttraction(String name){
        return repository.deleteAttraction(name);
    }

    public TouristAttraction updateAttraction(TouristAttraction touristAttraction) {
        return repository.updateAttraction(touristAttraction);
    }

    public ArrayList<String> getAllCities(){
        return repository.getAllCities();
    }

    public ArrayList<String> getAllTags(){
        return repository.getAllTags();
    }*/
}

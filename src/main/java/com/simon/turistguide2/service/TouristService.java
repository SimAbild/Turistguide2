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


    public ArrayList<TouristAttraction> getTouristAttractions() {
        return repository.getTouristAttractions();
    }

    public TouristAttraction findAttractionByName(String name){
       return repository.findAttractionByName(name);
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
        return repository.addAttraction(touristAttraction);
    }
    public ArrayList<TouristAttraction> deleteAttraction(String name){
        return repository.deleteAttraction(name);
    }

    public ArrayList<TouristAttraction> updateAttraction(TouristAttraction touristAttraction) {
        return repository.updateAttraction(touristAttraction);
    }
    public List<String> getAllCities(){
        return repository.getAllCities();
    }
    public List<String> getAllTags(){
        return repository.getAllTags();
    }
}

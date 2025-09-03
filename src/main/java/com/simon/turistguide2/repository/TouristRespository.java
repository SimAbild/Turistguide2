package com.simon.turistguide2.repository;

import com.simon.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRespository {
    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();


    public TouristRespository(){
        populateTouristAttractionList();
    }

    public void populateTouristAttractionList(){
        touristAttractions.add(new TouristAttraction("Tivoli","Forlystelsespark i KBH centrum"));
        touristAttractions.add(new TouristAttraction("Bakken","Der er altid noget om snakken, der er sjovt på Bakken"));
        touristAttractions.add(new TouristAttraction("Faarup","Der er altid noget sjov, ude i en skov"));
        touristAttractions.add(new TouristAttraction("Legoland","Leg godt"));
        touristAttractions.add(new TouristAttraction("BonBon land","Der er skideskægt i BonBon land"));
    }

    public ArrayList<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction findAttractionByName(String name){
        for (TouristAttraction touristAttraction : touristAttractions){
            if (touristAttraction.getName().equals(name)){
                return touristAttraction;
            }
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
        return touristAttraction;
    }

    public ArrayList<TouristAttraction> deleteAttraction(String name){
        for (int i = 0; i < touristAttractions.size(); i++) {
            if (touristAttractions.get(i).getName().equals(name)) {
                touristAttractions.remove(i);
                return touristAttractions;

            }
        }
        return null;
    }

    public ArrayList<TouristAttraction> updateAttraction(TouristAttraction touristAttraction) {
        for(TouristAttraction touristAttraction1 : touristAttractions){
            if(touristAttraction.getName().equals(touristAttraction1.getName())){
                touristAttraction1.setDescription(touristAttraction.getDescription());
            }
        }
        return touristAttractions;
    }

}

package com.simon.turistguide2.repository;

import com.simon.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class TouristRespository {
    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();
    private final ArrayList<String> cities = new ArrayList<>();
    private final ArrayList<String> tags = new ArrayList<>();


    public TouristRespository(){
        populateTouristAttractionList();
        populateCities();
        populateTags();
    }

    public ArrayList<String> getAllCities() {
        return cities;
    }

    public ArrayList<String> getAllTags() {
        return tags;
    }

    public void populateTags(){
        tags.add("Family friendly");
        tags.add("Free");
        tags.add("Museum");
        tags.add("Student discount");

    }

    public void populateCities(){
        cities.add("Copenhagen");
        cities.add("Aarhus");
        cities.add("Odense");
    }

    public void populateTouristAttractionList(){
        ArrayList<String> tivoliTags = new ArrayList<>();
        tivoliTags.add("Family friendly");

        ArrayList<String> nyhavnTags = new ArrayList<>();
        nyhavnTags.add("Family friendly");
        nyhavnTags.add("Free");

        ArrayList<String> aRoSTags = new ArrayList<>();
        aRoSTags.add("Family friendly");
        aRoSTags.add("Museum");
        aRoSTags.add("Student discount");

        ArrayList<String> oldTownTags = new ArrayList<>();
        oldTownTags.add("Museum");
        oldTownTags.add("Family friendly");

        ArrayList<String> HCTags = new ArrayList<>();
        HCTags.add("Museum");
        HCTags.add("Family friendly");

        touristAttractions.add(new TouristAttraction("Tivoli","Amusement park and garden in central Copenhagen", "Copenhagen",tivoliTags));
        touristAttractions.add(new TouristAttraction("Nyhavn","Iconic harbour in central Copenhagen", "Copenhagen", nyhavnTags));
        touristAttractions.add(new TouristAttraction("ARoS","One of Denmark's largest art collections", "Aarhus", aRoSTags));
        touristAttractions.add(new TouristAttraction("The Old Town Museum","Experience Danish history", "Aarhus", oldTownTags));
        touristAttractions.add(new TouristAttraction("H.C. Andersen's House","Enter the fairy tale", "Odense", HCTags));
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

    public TouristAttraction deleteAttraction(String name){

       TouristAttraction td = findAttractionByName(name);

           for (int i = 0; i < touristAttractions.size(); i++){
               if (td.getName().equals(touristAttractions.get(i).getName())){
                   touristAttractions.remove(td);
               }
           }
           return td;
    }


    public TouristAttraction updateAttraction(TouristAttraction touristAttraction) {
        for(TouristAttraction touristAttraction1 : touristAttractions){
            if(touristAttraction.getName().equals(touristAttraction1.getName())){
                touristAttraction1.setDescription(touristAttraction.getDescription());
                touristAttraction1.setCity(touristAttraction.getCity());
                touristAttraction1.setTags(touristAttraction.getTags());
            }
        }
        return touristAttraction;
    }

}

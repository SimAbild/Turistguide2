package com.simon.turistguide2.repository;

import com.simon.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


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
        tags.add("Børnevenlig");
        tags.add("Gratis");
        tags.add("Natur");
        tags.add("Museum");
        tags.add("Studierabat");

    }

    public void populateCities(){
        cities.add("København");
        cities.add("Aarhus");
        cities.add("Odense");
    }

    public void populateTouristAttractionList(){
        ArrayList<String> tivoliTags = new ArrayList<>();
        tivoliTags.add("Børnevenlig");
        tivoliTags.add("Tivoli");

        ArrayList<String> bakkenTags = new ArrayList<>();
        bakkenTags.add("Bakken");
        bakkenTags.add("Sjov");

        ArrayList<String> faarupTags = new ArrayList<>();
        faarupTags.add("Faarup");
        faarupTags.add("Ballade");

        ArrayList<String> legolandTags = new ArrayList<>();
        legolandTags.add("Legoland");
        legolandTags.add("Farver");

        ArrayList<String> bonbonLandTags = new ArrayList<>();
        bonbonLandTags.add("Bonbon land");
        bonbonLandTags.add("Hoppelig hop");

        touristAttractions.add(new TouristAttraction("Tivoli","Amusement park and garden in central Copenhagen", "Copenhagen",tivoliTags));
        touristAttractions.add(new TouristAttraction("Nyhavn","Iconic harbour in central Copenhagen", "Copenhagen", bakkenTags));
        touristAttractions.add(new TouristAttraction("ARoS","One of Denmark's largest art collections", "Aarhus", faarupTags));
        touristAttractions.add(new TouristAttraction("The Old Town Museum","Experience Danish history", "Aarhus", legolandTags));
        touristAttractions.add(new TouristAttraction("H.C. Andersen's House","Enter the fairy tale", "Odense", bonbonLandTags));
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

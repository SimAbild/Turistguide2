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

    public List<String> getAllCities() {
        return cities;
    }

    public ArrayList<String> getAllTags() {
        return tags;
    }

    public void populateTags(){
        tags.add("børnevenlig");
        tags.add("gratis");
        tags.add("natur");
        tags.add("museum");
        tags.add("studierabat");

    }

    public void populateCities(){
        cities.add("kbh");
        cities.add("Aarhus");
        cities.add("næstved");
    }

    public void populateTouristAttractionList(){
        ArrayList<String> tivoliTags = new ArrayList<>();
        tivoliTags.add("børnevenlig");
        tivoliTags.add("tivoli");
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
        touristAttractions.add(new TouristAttraction("Tivoli","Forlystelsespark i KBH centrum", "kbh",tivoliTags));
        touristAttractions.add(new TouristAttraction("Bakken","Der er altid noget om snakken, der er sjovt på Bakken", "klampenborg", bakkenTags));
        touristAttractions.add(new TouristAttraction("Faarup","Der er altid noget sjov, ude i en skov", "Aarhus", faarupTags));
        touristAttractions.add(new TouristAttraction("Legoland","Leg godt", "Billund", legolandTags));
        touristAttractions.add(new TouristAttraction("BonBon land","Der er skideskægt i BonBon land", "næstved", bonbonLandTags));
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

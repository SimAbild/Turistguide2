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
        tags.add("Natur");
        tags.add("Museum");
        tags.add("Studierabat");
        tags.add("Dyr");
        tags.add("Kultur");
        tags.add("Forlystelser");
        tags.add("Musik");
        tags.add("Sjælland");
        tags.add("Fyn");
        tags.add("Jylland");
        tags.add("Bonrholm");

    }

    public void populateCities(){
        cities.add("København");
        cities.add("Aarhus");
        cities.add("Næstved");
        cities.add("Odense");
        cities.add("Helsingør");
        cities.add("Esbjerg");
        cities.add("Køge");
        cities.add("Roskilde");
        cities.add("Rønne");
        cities.add("Hellerup");
    }

    public void populateTouristAttractionList(){
        ArrayList<String> tivoliTags = new ArrayList<>();
        tivoliTags.add("Børnevenlig");
        tivoliTags.add("Forlystelser");
        tivoliTags.add("Musik");

        ArrayList<String> kbhZooTags = new ArrayList<>();
        kbhZooTags.add("Dyr");
        kbhZooTags.add("Natur");
        kbhZooTags.add("Børnevenlig");

        ArrayList<String> faarupTags = new ArrayList<>();
        faarupTags.add("Faarup");
        faarupTags.add("Ballade");

        ArrayList<String> legolandTags = new ArrayList<>();
        legolandTags.add("Legoland");
        legolandTags.add("Farver");

        ArrayList<String> bonbonLandTags = new ArrayList<>();
        bonbonLandTags.add("Bonbon land");
        bonbonLandTags.add("Hoppelig hop");

        touristAttractions.add(new TouristAttraction("Tivoli","Forlystelsespark i KBH centrum", "København",tivoliTags));
        touristAttractions.add(new TouristAttraction("KBH Zoo","Zoologisk have ved Frederiksberg have", "København", kbhZooTags));
        touristAttractions.add(new TouristAttraction("Faarup","Der er altid noget sjov, ude i en skov", "Aarhus", faarupTags));
        touristAttractions.add(new TouristAttraction("Legoland","Leg godt", "Billund", legolandTags));
        touristAttractions.add(new TouristAttraction("BonBon land","Der er skideskægt i BonBon land", "Næstved", bonbonLandTags));
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

package com.simon.turistguide2.repository;

import com.simon.turistguide2.model.City;
import com.simon.turistguide2.model.Tag;
import com.simon.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
public class TouristRespository {
    private final JdbcTemplate jdbcTemplate;
    //RowMapper interface-attributter, der tager et data-table og antallet af rækker der i.
    //RowMapper attributterne konverterer indholdet af data i en række til et java objekt.
    //En attribut for hver af vores tables
    private final RowMapper<TouristAttraction> attractionRowMapper = (rs, rowNum) -> {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setName(rs.getString("name"));
        touristAttraction.setDescription(rs.getString("description"));
        touristAttraction.setCityID(rs.getInt("cityID"));
        touristAttraction.setAttractionID(rs.getInt("attractionID"));
        touristAttraction.setCityName(rs.getString("cityName"));
        return touristAttraction;
    };
    private final RowMapper<City> cityRowMapper = (rs, rowNum) -> {
        City city = new City();
        city.setCityID(rs.getInt("cityID"));
        city.setName(rs.getString("name"));
        return city;
    };
    private final RowMapper<Tag> tagRowMapper = (rs, rowNum) -> {
        Tag tags = new Tag();
        tags.setTagID(rs.getInt("tagID"));
        tags.setName(rs.getString("name"));
        tags.setAttractionID(rs.getInt("attractionID"));
        return tags;
    };

    public TouristRespository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //query metoden læser fra hele tabellen og rowMapper attributten smider så et java objekt for hvert række ind i en liste.
    public List<City> getAllCities() {
        List<City> allCities = jdbcTemplate.query("SELECT * FROM cities", cityRowMapper);
        return allCities;
    }

    //query metoden læser fra hele tabellen og rowMapper attributten smider så et java objekt for hvert række ind i en liste.
    public List<Tag> getAllTags() {
        List<Tag> allTags = jdbcTemplate.query("SELECT * FROM tags", tagRowMapper);
        return allTags;
    }


    //query metoden læser fra hele tabellen og rowMapper attributten smider så et java objekt for hvert række ind i en liste.
    public List<TouristAttraction> getTouristAttractions() {
        String sql = """
        SELECT
        a.attractionID,
        a.name,
        a.description,
        a.cityID,
        c.name AS cityName
        FROM attractions a
        JOIN cities c ON a.cityID = c.cityID
        """;

        List<TouristAttraction> touristAttractions = jdbcTemplate.query(sql, attractionRowMapper);
        return touristAttractions;
    }

    //finder attraktion ved at angive et id og returnerer så en liste med kun ét element, som er attraktionen med det givene id.
    public List<TouristAttraction> findAttractionByID(int attractionID) {
        String sql = "SELECT * FROM attractions WHERE attractionID = ?";
        return jdbcTemplate.query(sql, attractionRowMapper, attractionID);
    }

    //finder city ved at angive et id og returnerer så en liste med kun ét element, som er den city med det givene id.
    public List<City> findCityByID(int cityID) {
        String sql = "SELECT * FROM cities WHERE cityID = ?";
        return jdbcTemplate.query(sql, cityRowMapper,cityID);
    }

    //finder tag ved at angive et id og returnerer så en liste med kun ét element, som er det tag med det givene id.
    public List<Tag> findTagByID(int tagID) {
        String sql = "SELECT * FROM cities WHERE tagID = ?";
        return jdbcTemplate.query(sql, tagRowMapper, tagID);
    }

    //Tilføjer en attraktion til attraktions-tabelllen
    public TouristAttraction addAttraction(String name, String description, int cityID) {
        String sql = "INSERT INTO attractions (name, description, cityID) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, cityID);
            return ps;
        }, keyHolder);

        int attractionID = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;

        if (attractionID != -1) {
            return new TouristAttraction(attractionID, name, description, cityID);
        } else {
            throw new RuntimeException("Kunne ikke indsætte person");
        }
    }

/*

//Fjerner attraktion
    public TouristAttraction deleteAttraction(String name) {

        TouristAttraction td = findAttractionByName(name);

        for (int i = 0; i < touristAttractions.size(); i++) {
            if (td.getName().equals(touristAttractions.get(i).getName())) {
                touristAttractions.remove(td);
            }
        }
        return td;
    }

//Gemmer aendringer af attraktion
    public TouristAttraction updateAttraction(TouristAttraction touristAttraction) {
        for (TouristAttraction touristAttraction1 : touristAttractions) {
            if (touristAttraction.getName().equals(touristAttraction1.getName())) {
                touristAttraction1.setDescription(touristAttraction.getDescription());
                touristAttraction1.setCity(touristAttraction.getCity());
                touristAttraction1.setTags(touristAttraction.getTags());
            }
        }
        return touristAttraction;
    }*/
}
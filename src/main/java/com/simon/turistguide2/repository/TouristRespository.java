package com.simon.turistguide2.repository;

import com.simon.turistguide2.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRespository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TouristAttraction> rowMapper = (rs, rowNum) -> {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setName(rs.getString("attractionName"));
        touristAttraction.setDescription(rs.getString("description"));
        touristAttraction.setCityID(rs.getInt("cityID"));
        touristAttraction.setAttractionID(rs.getInt("attractionID"));
        return touristAttraction;
    };

    public TouristRespository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTableData() {

        jdbcTemplate.execute("CREATE TABLE attractions (" +
                "attractionID INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "cityID INT, " +
                "FOREIGN KEY (cityID) REFERENCES cities(cityID))" +
                "description VARCHAR(1000) NOT NULL, ");
    }
    /*
    public ArrayList<String> getAllCities() {

    }

    public ArrayList<String> getAllTags() {

    }
    */

    public List<TouristAttraction> getTouristAttractions() {
        List<TouristAttraction> touristAttractions = jdbcTemplate.query("SELECT * FROM attractions", rowMapper);
        return touristAttractions;
    }

    public List<TouristAttraction> findAttractionByID(int attractionID) {
        String sql = "SELECT * FROM attractions WHERE attractionID = ?";
        return jdbcTemplate.query(sql, rowMapper, attractionID);
    }

/*
    public TouristAttraction deleteAttraction(String name) {

        TouristAttraction td = findAttractionByName(name);

        for (int i = 0; i < touristAttractions.size(); i++) {
            if (td.getName().equals(touristAttractions.get(i).getName())) {
                touristAttractions.remove(td);
            }
        }
        return td;
    }

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

    public TouristAttraction addAttraction(String name, int cityID, String description) {
        String sql = "INSERT INTO attractions (name, cityID, description) VALUES (?, ?, ?)";
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

}

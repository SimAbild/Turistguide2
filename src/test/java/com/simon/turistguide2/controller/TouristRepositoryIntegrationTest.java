package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.repository.TouristRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristRepositoryIntegrationTest {

    @Autowired
    private TouristRespository repository;

    @Test
    void testReadAllAttractions() {
        List<TouristAttraction> all = repository.getTouristAttractions();

        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getName()).isEqualTo("Test Attraction");
        assertThat(all.get(0).getCityName()).isEqualTo("Copenhagen");
    }


    @Test
    void insertAndReadBack() {
        // Inserter en ny attraction
        TouristAttraction saved = repository.addAttraction("Test Attraction", "En test beskrivelse", 1);

        // Henter den tilbage via ID
        TouristAttraction found = repository.findAttractionByID(saved.getAttractionID());

        // Assertions
        assertThat(found.getName()).isEqualTo("Test Attraction");
        assertThat(found.getDescription()).isEqualTo("En test beskrivelse");
        assertThat(found.getCityID()).isEqualTo(1);
    }
}

package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getLoginPage() throws Exception {
        mockMvc.perform(get("/attraction/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void doLogin() {
    }

    @Test
    void getForside() throws Exception {
        mockMvc.perform(get("/attraction"))
                .andExpect(status().isOk())
                .andExpect(view().name("forside"));
    }

    @Test
    void getTouristAttractions() throws Exception {
        mockMvc.perform(get("/attraction/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    void getAdminPage() throws Exception {
        mockMvc.perform(get("/attraction/adminpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("Admin_attractionList"));
    }

    @Test
    void attractionTag() {
    }

    @Test
    void addAttraction() throws Exception {
        TouristAttraction touristAttraction = new TouristAttraction
                ("Jensens Bøfhus", "bøfhus", "Copenhagen", asList("Free", "Student discount"));
        when(touristService.addAttraction(any(TouristAttraction.class))).thenReturn(touristAttraction);

        mockMvc.perform(post("/attraction/save")
                .param("name", "Jensens Bøfhus")
                .param("description", "bøfhus")
                .param("city", "Copenhagen")
                .param("tags", "Free", "Family", "Student discount"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attraction/adminpage"));

    }

    @Test
    void saveAttraction() {
    }

    @Test
    void editAttraction() {
    }

    @Test
    void updateAttraction() {
    }

    @Test
    void deleteAttraction() {
    }
}
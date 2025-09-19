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

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.aot.hint.TypeReference.listOf;
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
    void doLogin() throws Exception {
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
    void attractionTag() throws Exception {
        TouristAttraction touristAttraction = new TouristAttraction();
        when(touristService.findAttractionByName("Bakken")).thenReturn(touristAttraction);

        mockMvc.perform(get("/attraction/{name}/tags", "Bakken"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"));
    }

    @Test
    void addAttraction() throws Exception {
        mockMvc.perform(get("/attraction/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-add-form"));
    }

    @Test
    void saveAttraction() throws Exception {
        TouristAttraction touristAttraction = new TouristAttraction
                ("Jensens Bøfhus", "bøfhus", "Copenhagen", new ArrayList<>(List.of("Free")));
        when(touristService.addAttraction(any(TouristAttraction.class))).thenReturn(touristAttraction);

        mockMvc.perform(post("/attraction/save")
                        .param("name", "Jensens Bøfhus")
                        .param("description", "bøfhus")
                        .param("city", "Copenhagen")
                        .param("tags", "Free"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attraction/adminpage"));

        verify(touristService, times(1)).addAttraction(touristAttraction);
    }

    @Test
    void editAttraction() {
    }

    @Test
    void updateAttraction() {
    }

    @Test
    void deleteAttraction() throws Exception {
        TouristAttraction touristAttraction = new TouristAttraction();
        when(touristService.deleteAttraction("Bakken")).thenReturn(touristAttraction);

        mockMvc.perform(post("/attraction/delete/{name}", "Bakken"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attraction/adminpage"));

        verify(touristService, times(1)).deleteAttraction("Bakken");
    }
}
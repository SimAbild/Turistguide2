package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

    @Test
    void getLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void getForside() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("forside"));
    }

    @Test
    void getTouristAttractions() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    void getAdminPage() throws Exception {
        mockMvc.perform(get("/adminpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_attractionList"));
    }

    @Test
    void addAttractionForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-add-form"));
    }

    @Test
    void saveAttraction() throws Exception {
        TouristAttraction saved = new TouristAttraction(1, "Test Attract", "Desc", 1);
        when(touristService.addAttraction(any(), any(), anyInt())).thenReturn(saved);

        mockMvc.perform(post("/save")
                        .param("name", "Test Attract")
                        .param("description", "Desc")
                        .param("cityID", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/adminpage"));

        verify(touristService, times(1)).addAttraction("Test Attract", "Desc", 1);
    }

    @Test
    void editAttractionForm() throws Exception {
        TouristAttraction attraction = new TouristAttraction(1, "Test Attract", "Desc", 1);
        when(touristService.findAttractionByID(1)).thenReturn(attraction);

        mockMvc.perform(get("/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-edit-form"));
    }

    @Test
    void updateAttraction() throws Exception {
        mockMvc.perform(post("/update")
                        .param("attractionID", "1")
                        .param("name", "Updated Attract")
                        .param("description", "Updated Desc")
                        .param("cityID", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/adminpage"));

        verify(touristService, times(1)).updateAttraction(any(TouristAttraction.class), any());
    }

    @Test
    void deleteAttraction() throws Exception {
        mockMvc.perform(post("/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/adminpage"));

        verify(touristService, times(1)).deleteAttractionByID(1);
    }
}

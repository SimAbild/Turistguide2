package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("attraction")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping("/attractions")
    public String getTouristAttractions(Model model){
        ArrayList<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions",attractions);
        return "attractionList";
    }

    @GetMapping("/{name}")
        public String getAttractionByName(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("attraction" , touristAttraction);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String attractionTag(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("attraction", touristAttraction);
        return "tags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("addAttraction" , touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "attraction-add-form";
    }
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);

        return "redirect:/attraction/attractions";
    }


/*
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name, Model model){
        TouristAttraction attraction = touristService.deleteAttraction(name);
        if(attraction == null){
            return "attractionList";
        }
        return "attractionList";
    } */

    /*
    @PostMapping("/update/")
    public ArrayList<TouristAttraction>> updateAttraction(@RequestBody TouristAttraction touristAttraction) {
        ArrayList<TouristAttraction> updatedAttractions = touristService.updateAttraction(touristAttraction);
    } */
}

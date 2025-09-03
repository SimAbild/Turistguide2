package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("Attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping()
    public String getTouristAttractions(Model model){
        ArrayList<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions",attractions);
        return "attractionList";
    }

    @GetMapping("{name}")
        public TouristAttraction getAttractionByName(@PathVariable String name){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        if(touristAttraction == null){

        }

    }

    @PostMapping("/add")
    public TouristAttraction addAttraction(@RequestBody TouristAttraction touristAttraction) {
        TouristAttraction newAttraction = touristService.addAttraction(touristAttraction);

    }

    @PostMapping("/delete/{name}")
    public ArrayList<TouristAttraction>>deleteAttraction(@PathVariable String name){
        ArrayList<TouristAttraction> removedAttraction = touristService.deleteAttraction(name);
        if(removedAttraction == null){

        }

    }

    @PostMapping("/update/")
    public ArrayList<TouristAttraction>> updateAttraction(@RequestBody TouristAttraction touristAttraction) {
        ArrayList<TouristAttraction> updatedAttractions = touristService.updateAttraction(touristAttraction);
    }
}

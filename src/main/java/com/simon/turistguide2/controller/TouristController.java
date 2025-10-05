package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.City;
import com.simon.turistguide2.model.Tag;
import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    //viser login siden
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    //går til admin siden efter succefuldt login
    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/adminpage";
        }
        return "redirect:/login?error=true";
    }

    //viser forsiden
    @GetMapping()
    public String getForside() {
        return "forside";
    }

    //viser siden med alle attraktioner
    @GetMapping("/attractions")
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    //viser admin siden med alle attraktioner og funktioner
    @GetMapping("/adminpage")
    public String getAdminPage(Model model) {
        List<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "admin_attractionList";
    }

    @GetMapping("/{id}/tags")
    public String attractionTag(@PathVariable int id, Model model){
        TouristAttraction attraction = (TouristAttraction) touristService.findAttractionByID(id);
        List<Tag> tags = touristService.getTagsForAttraction(id);

        attraction.setTags(tags); // kobler tags på objektet
        model.addAttribute("attraction", attraction);

        return "tags";
    }


    //viser add formen
    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "attraction-add-form";
    }


    @PostMapping("/save")
    public String saveAttraction(
            @ModelAttribute TouristAttraction attraction,
            @RequestParam(required = false) List<Integer> tagIDs) {

        // Gem attraktionen først
        TouristAttraction saved = touristService.addAttraction(
                attraction.getName(),
                attraction.getDescription(),
                attraction.getCityID()
        );

        // Hvis der er valgt tags, tilføjer dem med
        if (tagIDs != null && !tagIDs.isEmpty()) {
            touristService.addTagsToAttraction(saved.getAttractionID(), tagIDs);
        }

        return "redirect:/adminpage";
    }

/*
    //viser edit formen
    @GetMapping("/edit/{name}")
    public String editAttraction(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);

        model.addAttribute("attraction",touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());

        return "attraction-edit-form";
    }
    //går til admin siden efter succefuldt editering af attraktion
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/adminpage";
    }

 */

    @GetMapping("/edit/{id}")
    public String editAttraction(@PathVariable int id, Model model) {
        // Hent attraktion
        TouristAttraction attraction = touristService.findAttractionByID(id);

        // Hent alle byer
        List<City> allCities = touristService.getAllCities();

        // Hent alle tags
        List<Tag> allTags = touristService.getAllTags();

        // Hent attraktionens tags som ID’er
        List<Integer> selectedTagIDs = touristService.getTagIDsForAttraction(id);

        // Til Thymeleaf
        model.addAttribute("attraction", attraction);
        model.addAttribute("allCities", allCities);
        model.addAttribute("allTags", allTags);
        model.addAttribute("selectedTagIDs", selectedTagIDs);

        return "attraction-edit-form";
    }


    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction,
                                   @RequestParam(required = false) List<Integer> tagIDs) {
        touristService.updateAttraction(attraction, tagIDs);
        return "redirect:/adminpage";
    }



    @PostMapping("/delete/{id}")
    public String deleteAttractionByID(@PathVariable int id){
        touristService.deleteAttractionByID(id);
        return "redirect:/adminpage";
    }

}

package com.simon.turistguide2.controller;

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

    /*
    //viser siden med tags for en given attraktion
        @GetMapping("/{name}/tags")
        public String attractionTag(@PathVariable String name, Model model){
            TouristAttraction touristAttraction = touristService.findAttractionByName(name);
            model.addAttribute("attraction", touristAttraction);
            return "tags";
        }
    */
    //viser add formen
    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "attraction-add-form";
    }

    //går til admin siden efter succefuldt tilføjelse af attraktion
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addAttraction(touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getCityID());
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

    //går til admin siden efter succefuldt at have fjernet en attraktion
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name){
        touristService.deleteAttraction(name);
        return "redirect:/adminpage";
    }
*/
}

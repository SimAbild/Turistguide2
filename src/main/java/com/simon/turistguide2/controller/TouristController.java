package com.simon.turistguide2.controller;

import com.simon.turistguide2.model.TouristAttraction;
import com.simon.turistguide2.service.TouristService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/login")
        public String getLoginPage(){
        return "login";
        }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/attraction/adminpage";
        }
        return "redirect:/attraction/login?error=true";
    }

    @GetMapping()
    public String getForside(){
        return "forside";
    }

    @GetMapping("/attractions")
    public String getTouristAttractions(Model model){
        ArrayList<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions",attractions);
        return "attractionList";
    }

    @GetMapping("/adminpage")
    public String getAdminPage(Model model){
        ArrayList<TouristAttraction> attractions = touristService.getTouristAttractions();
        model.addAttribute("attractions",attractions);
        return "Admin_attractionList";
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
        model.addAttribute("attraction" , touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());
        return "attraction-add-form";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);
        return "redirect:/attraction/adminpage";
    }

    //Display the edit form
    @GetMapping("/edit/{name}")
    public String editAttraction(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);

        model.addAttribute("attraction",touristAttraction);
        model.addAttribute("allCities", touristService.getAllCities());
        model.addAttribute("allTags", touristService.getAllTags());

        return "attraction-edit-form";
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attraction/adminpage";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name){
        touristService.deleteAttraction(name);
        return "redirect:/attraction/adminpage";
    }


}

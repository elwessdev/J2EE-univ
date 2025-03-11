package com.isett.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("features", Arrays.asList(
//                "Travailler sur des projets avec un Tuteur",
//                "Avoir du retour sur expériences sur votre travail",
//                "Apprendre les meilleurs pratiques du Software Design"
//        ));
//        return "index";
//    }

    @GetMapping("/")
    public String home(@RequestParam(name = "showDetails", required = false) boolean showDetails, Model model) {
        model.addAttribute("features", Arrays.asList(
                "Travailler sur des projets avec un Tuteur",
                "Avoir du retour sur expériences sur votre travail",
                "Apprendre les meilleurs pratiques du Software Design"
        ));
        model.addAttribute("showDetails", showDetails);
        return "index";
    }

}

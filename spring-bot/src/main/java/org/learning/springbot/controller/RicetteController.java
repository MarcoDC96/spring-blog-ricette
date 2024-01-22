package org.learning.springbot.controller;

import org.learning.springbot.repository.RicetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ricette")
public class RicetteController {
    @Autowired
    private RicetteRepository ricetteRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ricetteList", ricetteRepository.findAll());
        return "ricette/list";
    }
}

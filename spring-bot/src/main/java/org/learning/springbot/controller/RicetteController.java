package org.learning.springbot.controller;

import jakarta.validation.Valid;
import org.learning.springbot.model.Ricette;
import org.learning.springbot.repository.RicetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicetteController {
    @Autowired
    private RicetteRepository ricetteRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ricetteList", ricetteRepository.findAll());
        return "ricette/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Ricette> result = ricetteRepository.findById(id);
        if (result.isPresent()) {
            ricetteRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", result.get().getTitolo() + " è cancellato!");
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id" + id + "not found");
        }
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ricette> result = ricetteRepository.findById(id);
        if (result.isPresent()) {
            Ricette ricette = result.get();
            model.addAttribute("ricette", ricette);
            return "ricette/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        Ricette ricette = new Ricette();
        model.addAttribute("ricette", ricette);
        return "ricette/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricette") Ricette formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ricette/create";
        }
        Ricette savedRicetta = ricetteRepository.save(formRicetta);
        return "redirect:/ricette/show/" + savedRicetta.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Ricette> result = ricetteRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("ricette", result.get());
            return "ricette/edit";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricette with id" + id + "not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,@Valid @ModelAttribute("ricetta") Ricette formRicette, BindingResult bindingResult){
        Optional<Ricette> result = ricetteRepository.findById(id);
        if (result.isPresent()){
            Ricette ricetteToEdit = result.get();
            if (bindingResult.hasErrors()){
                return "ricette/edit";
            }
            formRicette.setFoto(ricetteToEdit.getFoto());
            Ricette saveRicetta = ricetteRepository.save(formRicette);
            return "redirect:/ricette/show/{id}" ;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricette with id" + id + "not found");
        }
    }

}

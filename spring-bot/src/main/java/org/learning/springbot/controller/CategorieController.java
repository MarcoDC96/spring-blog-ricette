package org.learning.springbot.controller;

import jakarta.validation.Valid;
import org.learning.springbot.model.Categorie;
import org.learning.springbot.repository.CategorieRepository;
import org.learning.springbot.repository.RicetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private RicetteRepository ricetteRepository;

    @GetMapping
    public String index(Model model){
        List<Categorie> categoriaList;
        categoriaList = categorieRepository.findAll();
        model.addAttribute("categorie", categoriaList);
        return "categorie/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Categorie> result = categorieRepository.findById(id);
        if (result.isPresent()) {
            Categorie deleteCategoria = result.get();
            categorieRepository.delete(deleteCategoria);
            return "redirect:/categorie";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Category with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        Categorie categorie = new Categorie();
        model.addAttribute("categorie", categorie);
        return "categorie/create";
    }
    @PostMapping("/create")
    public String category(@Valid @ModelAttribute("categorie") Categorie formCategorie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorie", categorieRepository.findAll());
            return "categorie/create";
        }
        Categorie savedCategorie = categorieRepository.save(formCategorie);
        return "redirect:/categorie";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Categorie> result = categorieRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("categorie", result.get());
            return "categorie/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("categorie") Categorie formCategorie, BindingResult bindingResult) {
        Optional<Categorie> result = categorieRepository.findById(id);
        if (result.isPresent()) {
            Categorie editCategorie = result.get();
            if (bindingResult.hasErrors()) {
                return "categorie/edit";
            }
            Categorie savedCategorie = categorieRepository.save(formCategorie);
            return "redirect:/categorie";

        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }
}

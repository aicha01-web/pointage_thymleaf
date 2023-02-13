package com.groupeisi.pointage.controller;

import com.groupeisi.pointage.domain.Professeur;
import com.groupeisi.pointage.entity.ProfesseurEntity;
import com.groupeisi.pointage.repository.ProfesseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ProfesseurController {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @GetMapping(value = "/professeur/add")
    public String add(ModelMap map) {
        ProfesseurEntity professeur = new ProfesseurEntity();
        map.addAttribute("professeur", professeur);
        return "professeur/add";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        ProfesseurEntity prof = professeurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id professeur invalide:" + id));

        model.addAttribute("professeur", prof);
        return "professeur/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        ProfesseurEntity prof = professeurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id professeur invalide:" + id));
        professeurRepository.deleteById(id);
        return "redirect:/professeur/getAll";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid ProfesseurEntity professeur,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            professeur.setId(id);
            return "professeur/edit";
        }

        professeurRepository.save(professeur);
        return "redirect:/professeur/getAll";
    }

    @PostMapping(value = "/professeur/save")
    public String save(ProfesseurEntity professeurEntity) {
        professeurRepository.save(professeurEntity);
        return "redirect:/professeur/getAll";
    }

    @GetMapping(value = "/professeur/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("professeursList", professeurRepository.findAll());

        return "professeur/list";
    }

    @GetMapping("/")
    public String index() {
        //map.addAttribute("professeursList", professeurRepository.findAll());
        return "dashboard";
    }

}

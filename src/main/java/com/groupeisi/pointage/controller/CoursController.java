package com.groupeisi.pointage.controller;

import com.groupeisi.pointage.entity.CoursEntity;
import com.groupeisi.pointage.repository.ClasseRepository;
import com.groupeisi.pointage.repository.CoursRepository;
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
public class CoursController {
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private ClasseRepository cr;

    @GetMapping(value = "/cours/add")
    public String add(ModelMap map) {
        CoursEntity cours = new CoursEntity();
        map.addAttribute("cours", cours);
        map.addAttribute("classes",cr.findAll());
        map.addAttribute("professeurs",professeurRepository.findAll());
        return "cours/add";
    }

    @GetMapping("/editCours/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        CoursEntity cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Cours invalide:" + id));

        model.addAttribute("cours", cours);
        model.addAttribute("classes",cr.findAll());
        model.addAttribute("professeurs",professeurRepository.findAll());
        return "cours/edit";
    }

    @GetMapping("/deleteCours/{id}")
    public String deleteCours(@PathVariable("id") int id, Model model) {
        CoursEntity cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Cours invalide:" + id));
        coursRepository.deleteById(cours.getId());
        return "redirect:/cours/getAll";
    }

    @PostMapping("/updateCours/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid CoursEntity cours,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            cours.setId(id);
            return "cours/edit";
        }

        coursRepository.save(cours);
        return "redirect:/cours/getAll";
    }

    @PostMapping(value = "/cours/save")
    public String save(CoursEntity coursEntity) {
        coursEntity.setStatut("Confirmé");
        coursRepository.save(coursEntity);
        return "redirect:/cours/getAll";
    }

    @GetMapping(value = "/cours/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("coursList", coursRepository.findAll());
        return "cours/list";
    }
}

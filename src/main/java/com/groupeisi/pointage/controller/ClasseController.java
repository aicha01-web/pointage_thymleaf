package com.groupeisi.pointage.controller;

import com.groupeisi.pointage.entity.ClasseEntity;
import com.groupeisi.pointage.repository.ClasseRepository;
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
public class ClasseController {
    
    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping(value = "/classe/add")
    public String add(ModelMap map) {
        ClasseEntity classe = new ClasseEntity();
        map.addAttribute("classe", classe);
        return "classe/add";
    }

    @GetMapping("/editClasse/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        ClasseEntity classe = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id classe invalide:" + id));
        model.addAttribute("classe", classe);
        return "classe/edit";
    }

    @GetMapping("/deleteClasse/{id}")
    public String deleteClasse(@PathVariable("id") int id, Model model) {
        ClasseEntity classe = classeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id classe invalide:" + id));
        classeRepository.deleteById(classe.getId());
        return "redirect:/classe/getAll";
    }

    @PostMapping("/updateClasse/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid ClasseEntity classe,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            classe.setId(id);
            return "classe/edit";
        }

        classeRepository.save(classe);
        return "redirect:/classe/getAll";
    }

    @PostMapping(value = "/classe/save")
    public String save(ClasseEntity classeEntity) {
        classeRepository.save(classeEntity);
        return "redirect:/classe/getAll";
    }

    @GetMapping(value = "/classe/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("classesList", classeRepository.findAll());

        return "classe/list";
    }

}

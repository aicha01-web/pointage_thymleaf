package com.groupeisi.pointage.controller;

import com.groupeisi.pointage.entity.EtudiantEntity;
import com.groupeisi.pointage.repository.ClasseRepository;
import com.groupeisi.pointage.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository cr;

    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/etudiant/add")
    public String add(ModelMap map) {
        EtudiantEntity etudiant = new EtudiantEntity();
        map.addAttribute("etudiant", etudiant);
        map.addAttribute("classes",cr.findAll());
        return "etudiant/add";
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editEtudiant/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        EtudiantEntity etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Etudiant invalide:" + id));

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("classes",cr.findAll());
        return "etudiant/edit";
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteEtudiant/{id}")
    public String deleteEtudiant(@PathVariable("id") int id, Model model) {
        EtudiantEntity etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Etudiant invalide:" + id));
        etudiantRepository.deleteById(etudiant.getId());
        return "redirect:/etudiant/getAll";
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/updateEtudiant/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid EtudiantEntity etudiant,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            etudiant.setId(id);
            return "etudiant/edit";
        }

        etudiantRepository.save(etudiant);
        return "redirect:/etudiant/getAll";
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/etudiant/save")
    public String save(EtudiantEntity etudiantEntity) {
        etudiantRepository.save(etudiantEntity);
        return "redirect:/etudiant/getAll";
    }

    @PostAuthorize("hasAuthority('ETUDIANT')")
    @GetMapping(value = "/etudiant/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("etudiantsList", etudiantRepository.findAll());

        return "etudiant/list";
    }
}

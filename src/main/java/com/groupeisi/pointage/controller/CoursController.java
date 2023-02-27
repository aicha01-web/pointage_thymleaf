package com.groupeisi.pointage.controller;

import com.groupeisi.pointage.entity.ClasseEntity;
import com.groupeisi.pointage.entity.CoursEntity;
import com.groupeisi.pointage.entity.EtudiantEntity;
import com.groupeisi.pointage.entity.ProfesseurEntity;
import com.groupeisi.pointage.repository.ClasseRepository;
import com.groupeisi.pointage.repository.CoursRepository;
import com.groupeisi.pointage.repository.EtudiantRepository;
import com.groupeisi.pointage.repository.ProfesseurRepository;
import com.groupeisi.pointage.service.MailService;
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
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CoursController {
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository cr;

    @PostAuthorize("hasAuthority('PROFESSEUR')")
    @GetMapping(value = "/cours/add")
    public String add(ModelMap map) {
        CoursEntity cours = new CoursEntity();
        map.addAttribute("cours", cours);
        map.addAttribute("classes",cr.findAll());
        map.addAttribute("professeurs",professeurRepository.findAll());
        return "cours/add";
    }

    @PostAuthorize("hasAuthority('PROFESSEUR')")
    @GetMapping("/editCours/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        CoursEntity cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Cours invalide:" + id));

        model.addAttribute("cours", cours);
        model.addAttribute("classes",cr.findAll());
        model.addAttribute("professeurs",professeurRepository.findAll());
        return "cours/edit";
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteCours/{id}")
    public String deleteCours(@PathVariable("id") int id, Model model) {
        CoursEntity cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id Cours invalide:" + id));
        coursRepository.deleteById(cours.getId());
        return "redirect:/cours/getAll";
    }

    @PostAuthorize("hasAuthority('PROFESSEUR')")
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

    @PostAuthorize("hasAuthority('PROFESSEUR')")
    @PostMapping(value = "/cours/save")
    public String save(CoursEntity coursEntity) {
        coursEntity.setStatut("Confirmé");
        coursRepository.save(coursEntity);
        ClasseEntity classe = coursEntity.getClasse();
        ProfesseurEntity prof = coursEntity.getProfesseur();
        mailService.sendSimpleMessage(prof.getEmail(),"Cours programmé","Un cours de "+
                coursEntity.getIntitule()+" pour la classe de "+coursEntity.getClasse().getNom()+" a été programmé à la" +
                " salle "+coursEntity.getSalle()+" le "+coursEntity.getDatecours()+ " "+ coursEntity.getHeure());
        List<EtudiantEntity> etudiants = etudiantRepository.findByClasse(classe);
        for (EtudiantEntity e:etudiants) {
            mailService.sendSimpleMessage(e.getEmail(),"Cours programmé","Un cours de "+
                    coursEntity.getIntitule()+" avec "+coursEntity.getProfesseur().getNom()+" "+coursEntity.getProfesseur().getPrenom()+" a été programmé à la" +
                    " salle "+coursEntity.getSalle()+" le "+coursEntity.getDatecours().toString()+ " "+ coursEntity.getHeure().toString());
        }
        return "redirect:/cours/getAll";
    }

    @PostAuthorize("hasAuthority('ETUDIANT')")
    @GetMapping(value = "/cours/getAll")
    public String getAll(ModelMap map) {
        map.addAttribute("coursList", coursRepository.findAll());
        return "cours/list";
    }
}

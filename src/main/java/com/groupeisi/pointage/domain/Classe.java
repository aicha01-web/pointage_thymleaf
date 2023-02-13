package com.groupeisi.pointage.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Classe {
    private int id;

    private String nom;

    private List<Etudiant> etudiants;
}

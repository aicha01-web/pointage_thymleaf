package com.groupeisi.pointage.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    private int id;

    private String nom;

    private String prenom;

    private String email;

    private String password;

    private Date datenaissance;

    private int numeroetudiant;



}

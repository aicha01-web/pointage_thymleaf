package com.groupeisi.pointage.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Professeur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfesseurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150,nullable = false)
    private String nom;

    @Column(length = 150,nullable = false)
    private String prenom;

    @Email
    @Column(length = 150,nullable = false,unique = true)
    private String email;

    @NotNull
    @Column(length = 20,nullable = false)
    private String password;

    @Column(length = 150,nullable = false)
    private int etat;
}

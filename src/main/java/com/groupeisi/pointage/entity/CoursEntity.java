package com.groupeisi.pointage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name="Cours")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @Column(length = 150,nullable = false,unique = true)
    private String intitule;

    @NotNull
    @Column(length = 20,nullable = false)
    private Date datecours;

    @NotNull
    @Column(length = 20,nullable = false)
    private String heure;

    @Column(length = 150,nullable = false)
    private String statut;

    @NotNull
    @Column(length = 20,nullable = false)
    private String salle;

    @ManyToOne
    @JoinColumn(name="prof_id", nullable=false)
    private ProfesseurEntity professeur;

    @ManyToOne
    @JoinColumn(name="classe_id", nullable=false)
    private ClasseEntity classe;
}

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
@Table(name="Etudiant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EtudiantEntity {
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

    @NotNull
    @Column(length = 20,nullable = false)
    private Date datenaissance;

    @NotNull
    @Column(length = 20,nullable = false)
    private int numeroetudiant;

    @ManyToOne
    @JoinColumn(name="classe_id", nullable=false)
    private ClasseEntity classe;
}

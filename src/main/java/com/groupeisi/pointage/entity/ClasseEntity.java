package com.groupeisi.pointage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Classe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClasseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150,nullable = false)
    private String nom;

    @OneToMany(mappedBy ="classe")
    private List<EtudiantEntity> etudiants;

    @OneToMany(mappedBy ="classe")
    private List<CoursEntity> cours;
}

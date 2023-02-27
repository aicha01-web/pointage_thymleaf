package com.groupeisi.pointage.repository;

import com.groupeisi.pointage.entity.ClasseEntity;
import com.groupeisi.pointage.entity.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity,Integer> {
    List<EtudiantEntity> findByClasse(ClasseEntity classe);
}

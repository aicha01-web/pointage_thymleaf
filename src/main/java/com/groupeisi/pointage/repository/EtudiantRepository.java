package com.groupeisi.pointage.repository;

import com.groupeisi.pointage.entity.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity,Integer> {
}

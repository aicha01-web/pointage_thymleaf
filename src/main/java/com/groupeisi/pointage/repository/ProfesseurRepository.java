package com.groupeisi.pointage.repository;

import com.groupeisi.pointage.entity.ProfesseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<ProfesseurEntity,Integer> {
}

package com.groupeisi.pointage.repository;

import com.groupeisi.pointage.entity.CoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursRepository extends JpaRepository<CoursEntity,Integer> {
    Optional<List<CoursEntity>> findByProfesseur(int id);
}

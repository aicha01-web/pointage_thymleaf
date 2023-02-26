package com.groupeisi.pointage.mapper;

import com.groupeisi.pointage.domain.Professeur;
import com.groupeisi.pointage.entity.ProfesseurEntity;
import org.mapstruct.Mapper;


@Mapper
public interface ProfesseurMapper {
    Professeur toProfesseur(ProfesseurEntity professeurEntity);
    ProfesseurEntity  fromProfesseur(Professeur professeur);
}

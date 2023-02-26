package com.groupeisi.pointage.mapper;

import com.groupeisi.pointage.domain.Etudiant;
import com.groupeisi.pointage.entity.EtudiantEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EtudiantMapper {
    Etudiant toEtudiant(EtudiantEntity etudiantEntity);
    EtudiantEntity  fromEtudiant(Etudiant etudiant);
}

package com.groupeisi.pointage.mapper;

import com.groupeisi.pointage.domain.Classe;
import com.groupeisi.pointage.entity.ClasseEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClasseMapper {
    Classe toClasse(ClasseEntity classeEntity);
    ClasseEntity fromClasse(Classe classe);

}

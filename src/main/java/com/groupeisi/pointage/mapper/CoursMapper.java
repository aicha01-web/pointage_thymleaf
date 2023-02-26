package com.groupeisi.pointage.mapper;

import com.groupeisi.pointage.domain.Cours;
import com.groupeisi.pointage.entity.CoursEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CoursMapper {
    Cours toCours(CoursEntity coursEntity);
    CoursEntity fromCours(Cours cours);
}

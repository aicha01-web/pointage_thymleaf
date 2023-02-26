package com.groupeisi.pointage.service;

import com.groupeisi.pointage.domain.Classe;
import com.groupeisi.pointage.exception.RequestException;
import com.groupeisi.pointage.mapper.ClasseMapper;
import com.groupeisi.pointage.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClasseService {
    ClasseRepository classeRepository;
    ClasseMapper classeMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Classe> getClasses() {
        return classeRepository.findAll().stream().map(classeMapper::toClasse).toList();
    }

    @Transactional(readOnly = true)
    public Classe getClasse(int id) {
        return classeMapper.toClasse(classeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public Classe createClasse(Classe classe) {
        classeRepository.findById(classe.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("classe.exists", new Object[]{classe.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return classeMapper.toClasse(classeRepository.save(classeMapper.fromClasse(classe)));
    }

    @Transactional
    public Classe updateClasse(int id, Classe classe){
        return classeRepository.findById(id)
                .map(entity -> {
                    classe.setId(id);
                    return classeMapper.toClasse(classeRepository.save(classeMapper.fromClasse(classe)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteClasse(int id) {
        try {
            classeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("classe.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}

package com.groupeisi.pointage.service;

import com.groupeisi.pointage.domain.Cours;
import com.groupeisi.pointage.exception.RequestException;
import com.groupeisi.pointage.mapper.CoursMapper;
import com.groupeisi.pointage.repository.CoursRepository;
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
public class CoursService {
    CoursRepository coursRepository;
    CoursMapper coursMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Cours> getCours() {
        return coursRepository.findAll().stream().map(coursMapper::toCours).toList();
    }

    @Transactional(readOnly = true)
    public Cours getCours(int id) {
        return coursMapper.toCours(coursRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("professeur.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public Cours createCours(Cours professeur) {
        coursRepository.findById(professeur.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("professeur.exists", new Object[]{professeur.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return coursMapper.toCours(coursRepository.save(coursMapper.fromCours(professeur)));
    }

    @Transactional
    public Cours updateCours(int id, Cours professeur){
        return coursRepository.findById(id)
                .map(entity -> {
                    professeur.setId(id);
                    return coursMapper.toCours(coursRepository.save(coursMapper.fromCours(professeur)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("professeur.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteCours(int id) {
        try {
            coursRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("professeur.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}

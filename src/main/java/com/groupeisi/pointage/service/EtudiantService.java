package com.groupeisi.pointage.service;

import com.groupeisi.pointage.domain.Etudiant;
import com.groupeisi.pointage.exception.RequestException;
import com.groupeisi.pointage.mapper.EtudiantMapper;
import com.groupeisi.pointage.repository.EtudiantRepository;
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
public class EtudiantService {
    EtudiantRepository etudiantRepository;
    EtudiantMapper etudiantMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Etudiant> getEtudiants() {
        return etudiantRepository.findAll().stream().map(etudiantMapper::toEtudiant).toList();
    }

    @Transactional(readOnly = true)
    public Etudiant getEtudiant(int id) {
        return etudiantMapper.toEtudiant(etudiantRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("etudiant.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public Etudiant createEtudiant(Etudiant etudiant) {
        etudiantRepository.findById(etudiant.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("etudiant.exists", new Object[]{etudiant.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return etudiantMapper.toEtudiant(etudiantRepository.save(etudiantMapper.fromEtudiant(etudiant)));
    }

    @Transactional
    public Etudiant updateEtudiant(int id, Etudiant etudiant){
        return etudiantRepository.findById(id)
                .map(entity -> {
                    etudiant.setId(id);
                    return etudiantMapper.toEtudiant(etudiantRepository.save(etudiantMapper.fromEtudiant(etudiant)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("etudiant.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteEtudiant(int id) {
        try {
            etudiantRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("etudiant.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}

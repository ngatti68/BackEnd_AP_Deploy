package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Experiencia;
import com.miportfolioweb.backend.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    public List<Experiencia> list(){
        return experienciaRepository.findAll();
    }

    public Optional<Experiencia> getOne(int id){
        return experienciaRepository.findById(id);
    }

    public Optional<Experiencia> getByNombreE(String nombreE){
        return experienciaRepository.findByNombreE(nombreE);
    }

    public void save(Experiencia expe){
        experienciaRepository.save(expe);
    }

    public void delete(int id){
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return experienciaRepository.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return experienciaRepository.existsByNombreE(nombreE);
    }

}

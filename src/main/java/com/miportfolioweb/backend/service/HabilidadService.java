package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Habilidad;
import com.miportfolioweb.backend.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    public List<Habilidad> list(){
        return habilidadRepository.findAll();
    }

    public Optional<Habilidad> getOne(int id){
        return habilidadRepository.findById(id);
    }

    public Optional<Habilidad> getByNombre(String nombre){
        return habilidadRepository.findByNombre(nombre);
    }

    public void save(Habilidad skills){
        habilidadRepository.save(skills);
    }

    public void delete(int id){
        habilidadRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return habilidadRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return habilidadRepository.existsByNombre(nombre);
    }

}

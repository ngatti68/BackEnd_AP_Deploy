package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Persona;
import com.miportfolioweb.backend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> list(){return personaRepository.findAll();
    }

    public Optional<Persona> getOne(int id){
        return personaRepository.findById(id);
    }

    public Optional<Persona> getByNombre(String nombre){
        return personaRepository.findByNombre(nombre);
    }

    public void save(Persona pers){
        personaRepository.save(pers);
    }

    public void delete(int id){
        personaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return personaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return personaRepository.existsByNombre(nombre);
    }

}

package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {

    public Optional<Persona> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);

}

package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    public Optional<Experiencia> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);

}

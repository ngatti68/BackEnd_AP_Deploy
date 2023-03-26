package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {
    Optional<Habilidad> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

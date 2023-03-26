package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}

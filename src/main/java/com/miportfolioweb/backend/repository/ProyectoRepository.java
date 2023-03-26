package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    public Optional<Proyecto> findByTituloP(String tituloP);
    public boolean existsByTituloP(String tituloP);
}

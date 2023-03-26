package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Rol;
import com.miportfolioweb.backend.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

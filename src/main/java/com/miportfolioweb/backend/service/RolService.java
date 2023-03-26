package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Rol;
import com.miportfolioweb.backend.enums.RolNombre;
import com.miportfolioweb.backend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}

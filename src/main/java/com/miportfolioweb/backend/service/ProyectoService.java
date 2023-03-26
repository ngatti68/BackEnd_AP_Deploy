package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Proyecto;
import com.miportfolioweb.backend.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository ;

    public List<Proyecto> list(){
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getOne(int id){
        return proyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByTituloP(String tituloP){
        return proyectoRepository.findByTituloP(tituloP);
    }

    public void save(Proyecto proy){
        proyectoRepository.save(proy);
    }

    public void delete(int id){
        proyectoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return proyectoRepository.existsById(id);
    }

    public boolean existsByTituloP(String tituloP){
        return proyectoRepository.existsByTituloP(tituloP);
    }

}

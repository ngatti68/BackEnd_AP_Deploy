package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.ExperienciaDTO;
import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.entity.Experiencia;
import com.miportfolioweb.backend.service.ExperienciaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("La experiencia no existe."), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getOne(id).orElse(null);
        return new ResponseEntity<>(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("La experiencia que buscaste no existe."), HttpStatus.NOT_FOUND);
        }
        experienciaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Experiencia eliminada correctamente."), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        if(experienciaService.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("Esta experiencia ya existe."), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(dtoexp.getTituloE(), dtoexp.getFechaE(),
                dtoexp.getNombreE(), dtoexp.getDescripcionE());
        experienciaService.save(experiencia);

        return new ResponseEntity<>(new Mensaje("Experiencia agregada satisfactoriamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO dtoexp){

        if(!experienciaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);

        if(experienciaService.existsByNombreE(dtoexp.getNombreE()) && experienciaService.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esta experiencia ya existe."), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = experienciaService.getOne(id).orElse(null);
        assert experiencia != null;
        experiencia.setTituloE(dtoexp.getTituloE());
        experiencia.setFechaE(dtoexp.getFechaE());
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE(dtoexp.getDescripcionE());

        experienciaService.save(experiencia);
        return new ResponseEntity<>(new Mensaje("Experiencia actualizada satisfactoriamente."), HttpStatus.OK);

    }

}

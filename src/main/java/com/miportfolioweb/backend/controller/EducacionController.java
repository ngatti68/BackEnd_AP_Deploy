package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.EducacionDTO;
import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.entity.Educacion;
import com.miportfolioweb.backend.service.EducacionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://ng-portfolioweb-ap.web.app")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = educacionService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")int id){
        if(!educacionService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).orElse(null);
        return new ResponseEntity<>(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!educacionService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.NOT_FOUND);
        }
        educacionService.delete(id);
        return new ResponseEntity<>(new Mensaje("Educación eliminada correctamente."), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody EducacionDTO dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if(educacionService.existsByNombreE(dtoeducacion.getNombreE())){
            return new ResponseEntity<>(new Mensaje("Este nombre ya existe."), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoeducacion.getTituloE(), dtoeducacion.getFechaE(),
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
        educacionService.save(educacion);
        return new ResponseEntity<>(new Mensaje("Educación agregada correctamente."), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDTO dtoeducacion){
        if(!educacionService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.NOT_FOUND);
        }
        if(educacionService.existsByNombreE(dtoeducacion.getNombreE()) && educacionService.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Este nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity<>(new Mensaje("Este campo no puede estar vacío."), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).orElse(null);
        assert educacion != null;
        educacion.setTituloE(dtoeducacion.getTituloE());
        educacion.setFechaE(dtoeducacion.getFechaE());
        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());

        educacionService.save(educacion);

        return new ResponseEntity<>(new Mensaje("Educación actualizada correctamente."), HttpStatus.OK);
    }

}

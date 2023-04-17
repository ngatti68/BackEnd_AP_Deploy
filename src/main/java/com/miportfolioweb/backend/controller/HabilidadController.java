package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.HabilidadDTO;
import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.entity.Habilidad;
import com.miportfolioweb.backend.service.HabilidadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/habilidad")
@CrossOrigin(origins = "https://ng-portfolioweb-ap.web.app")
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;

    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list(){
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!habilidadService.existsById(id))
            return new ResponseEntity<>(new Mensaje("Esta skill no existe."), HttpStatus.NOT_FOUND);
        Habilidad habilidad = habilidadService.getOne(id).orElse(null);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("La skill que buscaste no existe."), HttpStatus.NOT_FOUND);
        }
        habilidadService.delete(id);
        return new ResponseEntity<>(new Mensaje("Skill eliminada correctamente."), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody HabilidadDTO dtoskills){
        if(StringUtils.isBlank(dtoskills.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        if(habilidadService.existsByNombre(dtoskills.getNombre()))
            return new ResponseEntity<>(new Mensaje("Esta skill ya existe."), HttpStatus.BAD_REQUEST);

        Habilidad skills = new Habilidad(dtoskills.getNombre(), dtoskills.getPorcentaje(), dtoskills.getUrlImagen(), dtoskills.getColor());
        habilidadService.save(skills);

        return new ResponseEntity<>(new Mensaje("Skill agregada satisfactoriamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HabilidadDTO habilidadDTO){

        int percentage = habilidadDTO.getPorcentaje();

        if(!habilidadService.existsById(id))
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);

        if(habilidadService.existsByNombre(habilidadDTO.getNombre()) && Objects.requireNonNull(habilidadService.getByNombre(habilidadDTO.getNombre()).orElse(null)).getId() != id)
            return new ResponseEntity<>(new Mensaje("Esta skill ya existe."), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(habilidadDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(Integer.toString(percentage)))
            return new ResponseEntity<>(new Mensaje("El porcentaje es obligatorio."), HttpStatus.BAD_REQUEST);

        Habilidad skills = habilidadService.getOne(id).orElse(null);
        assert skills != null;
        skills.setNombre(habilidadDTO.getNombre());
        skills.setPorcentaje(habilidadDTO.getPorcentaje());

        habilidadService.save(skills);
        return new ResponseEntity<>(new Mensaje("Skill actualizada satisfactoriamente."), HttpStatus.OK);

    }

}

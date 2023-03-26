package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.DTO.ProyectoDTO;
import com.miportfolioweb.backend.entity.Proyecto;
import com.miportfolioweb.backend.service.ProyectoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://ng-portfolioweb-ap.web.app/")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("El proyecto no existe."), HttpStatus.NOT_FOUND);
        }
        Proyecto proy = proyectoService.getOne(id).orElse(null);
        return new ResponseEntity<>(proy, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("El proyecto que buscaste no existe."), HttpStatus.NOT_FOUND);
        }
        proyectoService.delete(id);
        return new ResponseEntity<>(new Mensaje("Proyecto eliminado correctamente."), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ProyectoDTO dtoproy) {
        if (StringUtils.isBlank(dtoproy.getTituloP())) {
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (proyectoService.existsByTituloP(dtoproy.getTituloP())) {
            return new ResponseEntity<>(new Mensaje("Este proyecto ya existe."), HttpStatus.BAD_REQUEST);
        }

        Proyecto proy = new Proyecto(dtoproy.getTituloP(), dtoproy.getDescripcionP(), dtoproy.getDemoP(),
                dtoproy.getRepoP(), dtoproy.getUrlimagenP());
        proyectoService.save(proy);

        return new ResponseEntity<>(new Mensaje("Proyecto agregado satisfactoriamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectoDTO dtoproy) {

        if (!proyectoService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);
        }

        if (proyectoService.existsByTituloP(dtoproy.getTituloP()) && proyectoService.getByTituloP(dtoproy.getTituloP()).orElse(null).getId() != id) {
            return new ResponseEntity<>(new Mensaje("Este proyecto ya existe."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoproy.getTituloP())) {
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        Proyecto proy = proyectoService.getOne(id).orElse(null);
        assert proy != null;
        proy.setTituloP(dtoproy.getTituloP());
        proy.setDescripcionP(dtoproy.getDescripcionP());
        proy.setDemoP(dtoproy.getDemoP());
        proy.setRepoP(dtoproy.getRepoP());
        proy.setUrlimagenP(dtoproy.getUrlimagenP());

        proyectoService.save(proy);
        return new ResponseEntity<>(new Mensaje("Proyecto actualizado satisfactoriamente."), HttpStatus.OK);

    }
}

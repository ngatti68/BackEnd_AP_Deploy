package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.DTO.PersonaDTO;
import com.miportfolioweb.backend.entity.Persona;
import com.miportfolioweb.backend.service.PersonaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "https://ng-portfolioweb-ap.web.app")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).orElse(null);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody PersonaDTO personaDTO){
        Persona persona = new Persona(personaDTO.getNombre(), personaDTO.getApellido(), personaDTO.getImg(),
                personaDTO.getTitulo(), personaDTO.getDescripcion());
        personaService.save(persona);

        return new ResponseEntity<>(new Mensaje("Persona agregada satisfactoriamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDTO personaDTO){
        if(!personaService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.NOT_FOUND);
        }
        if(personaService.existsByNombre(personaDTO.getNombre()) && personaService.getByNombre(personaDTO.getNombre()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Este nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDTO.getNombre())){
            return new ResponseEntity<>(new Mensaje("Este campo no puede estar vacío."), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).orElse(null);
        assert persona != null;
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setImg(personaDTO.getImg());
        persona.setTitulo(personaDTO.getTitulo());
        persona.setDescripcion(personaDTO.getDescripcion());

        personaService.save(persona);

        return new ResponseEntity<>(new Mensaje("Persona actualizada correctamente."), HttpStatus.OK);
    }

}

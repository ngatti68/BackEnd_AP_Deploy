package com.miportfolioweb.backend.DTO;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonaDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String img;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;

    public PersonaDTO() {
    }

    public PersonaDTO(String nombre, String apellido, String img, String titulo, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.img = img;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

}

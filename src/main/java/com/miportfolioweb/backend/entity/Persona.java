package com.miportfolioweb.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 1, max = 70, message = "Es demasiado corto o demasiado largo.")
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 70, message = "Es demasiado corto o demasiado largo.")
    private String apellido;

    @NotNull
    @Size(min = 1, max = 400, message = "Es demasiado corto o demasiado largo.")
    private String img;

    @NotBlank
    @Size(min = 1, max = 70, message = "Es demasiado corto o demasiado largo.")
    private String titulo;

    @NotBlank
    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String descripcion;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String img, String titulo, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.img = img;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

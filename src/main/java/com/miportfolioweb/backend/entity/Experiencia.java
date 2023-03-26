package com.miportfolioweb.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "experiencia")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tituloE;
    private String fechaE;
    private String nombreE;

    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String descripcionE;

    public Experiencia() {
    }

    public Experiencia(String tituloE, String fechaE, String nombreE, String descripcionE) {
        this.tituloE = tituloE;
        this.fechaE = fechaE;
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloE() {
        return tituloE;
    }

    public void setTituloE(String tituloE) {
        this.tituloE = tituloE;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
}

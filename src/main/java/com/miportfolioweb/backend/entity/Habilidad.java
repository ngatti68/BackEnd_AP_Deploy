package com.miportfolioweb.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "habilidad")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int porcentaje;
    private String urlImagen;
    private String color;

    public Habilidad() {
    }

    public Habilidad(String nombre, int porcentaje, String urlImagen, String color) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.urlImagen = urlImagen;
        this.color = color;
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

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

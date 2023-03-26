package com.miportfolioweb.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class HabilidadDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;

    @NotBlank
    private String urlImagen;
    @NotBlank
    private String color;

    public HabilidadDTO() {
    }

    public HabilidadDTO(String nombre, int porcentaje, String urlImagen, String color) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.urlImagen = urlImagen;
        this.color = color;
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

package com.miportfolioweb.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProyectoDTO {

    @NotBlank
    private String tituloP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String demoP;
    @NotBlank
    private String repoP;
    @NotBlank
    private String urlimagenP;

    public ProyectoDTO() {
    }

    public ProyectoDTO(String tituloP, String descripcionP, String demoP, String repoP, String urlimagenP) {
        this.tituloP = tituloP;
        this.descripcionP = descripcionP;
        this.demoP = demoP;
        this.repoP = repoP;
        this.urlimagenP = urlimagenP;
    }
}

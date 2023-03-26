package com.miportfolioweb.backend.DTO;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EducacionDTO {

    @NotBlank
    private String tituloE;
    @NotBlank
    private String fechaE;
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;

    public EducacionDTO() {
    }

    public EducacionDTO(String tituloE, String fechaE, String nombreE, String descripcionE) {
        this.tituloE = tituloE;
        this.fechaE = fechaE;
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
}

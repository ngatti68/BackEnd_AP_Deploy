package com.miportfolioweb.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BannerDTO {

    @NotBlank
    private String urlimg;

    public BannerDTO() {
    }

    public BannerDTO(String urlimg) {
        this.urlimg = urlimg;
    }

}

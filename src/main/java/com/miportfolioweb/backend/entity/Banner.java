package com.miportfolioweb.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String urlimg;

    public Banner() {
    }

    public Banner(String urlimg) {
        this.urlimg = urlimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }
}

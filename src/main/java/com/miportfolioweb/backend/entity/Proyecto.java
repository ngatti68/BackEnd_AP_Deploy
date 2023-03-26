package com.miportfolioweb.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tituloP;

    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String descripcionP;

    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String demoP;

    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String repoP;

    @Size(min = 1, max = 800, message = "Es demasiado corto o demasiado largo.")
    private String urlimagenP;

    public Proyecto() {
    }

    public Proyecto(String tituloP, String descripcionP, String demoP, String repoP, String urlimagenP) {
        this.tituloP = tituloP;
        this.descripcionP = descripcionP;
        this.demoP = demoP;
        this.repoP = repoP;
        this.urlimagenP = urlimagenP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloP() {
        return tituloP;
    }

    public void setTituloP(String tituloP) {
        this.tituloP = tituloP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getDemoP() {
        return demoP;
    }

    public void setDemoP(String demoP) {
        this.demoP = demoP;
    }

    public String getRepoP() {
        return repoP;
    }

    public void setRepoP(String repoP) {
        this.repoP = repoP;
    }

    public String getUrlimagenP() {
        return urlimagenP;
    }

    public void setUrlimagenP(String urlimagenP) {
        this.urlimagenP = urlimagenP;
    }
}

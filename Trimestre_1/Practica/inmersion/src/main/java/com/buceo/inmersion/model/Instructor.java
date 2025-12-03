package com.buceo.inmersion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Boolean certificado;
    private Integer anosExperiencia;
    private String contacto;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroBuceo centro;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getCertificado() {
        return certificado;
    }


    public void setCertificado(Boolean certificado) {
        this.certificado = certificado;
    }
    public Integer getAnosExperiencia() {
        return anosExperiencia;
    }
    public void setAnosExperiencia(Integer anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }
    public String getContacto() {
        return contacto;
    }
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    public CentroBuceo getCentro() {
        return centro;
    }
    public void setCentro(CentroBuceo centro) {
        this.centro = centro;
    }
    
}

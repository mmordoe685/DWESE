package com.buceo.inmersion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nivelBuceo;
    private Integer experiencia;
    private Boolean certificadoMedico;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getNivelBuceo() {
        return nivelBuceo;
    }
    public void setNivelBuceo(Integer nivelBuceo) {
        this.nivelBuceo = nivelBuceo;
    }
    public Integer getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }
    public Boolean getCertificadoMedico() {
        return certificadoMedico;
    }
    public void setCertificadoMedico(Boolean certificadoMedico) {
        this.certificadoMedico = certificadoMedico;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}

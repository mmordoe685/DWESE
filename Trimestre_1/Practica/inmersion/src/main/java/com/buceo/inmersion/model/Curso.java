package com.buceo.inmersion.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer nivel;
    private Double precio;
    private Integer duracion;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroBuceo centro;

    @OneToMany(mappedBy = "curso")
    private List<UsuarioCurso> usuarios;

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
    public Integer getNivel() {
        return nivel;
    }
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public CentroBuceo getCentro() {
        return centro;
    }
    public void setCentro(CentroBuceo centro) {
        this.centro = centro;
    }
    public List<UsuarioCurso> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<UsuarioCurso> usuarios) {
        this.usuarios = usuarios;
    }
    
}

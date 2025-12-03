package com.buceo.inmersion.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "sitio_buceo")
public class SitioBuceo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;
    private Integer tipo;
    private Integer dificultad;
    private String descripcion;

    @ManyToMany(mappedBy = "sitios")
    private List<Inmersion> inmersiones;

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
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Integer getTipo() {
        return tipo;
    }
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    public Integer getDificultad() {
        return dificultad;
    }
    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<Inmersion> getInmersiones() {
        return inmersiones;
    }
    public void setInmersiones(List<Inmersion> inmersiones) {
        this.inmersiones = inmersiones;
    }
    
}

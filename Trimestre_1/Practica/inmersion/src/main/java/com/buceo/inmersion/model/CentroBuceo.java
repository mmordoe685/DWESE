package com.buceo.inmersion.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "centro_buceo")
public class CentroBuceo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;
    private String telefono;
    private String correoContacto;
    private String sitioWeb;

    @OneToMany(mappedBy = "centro")
    private List<Instructor> instructores;

    @OneToMany(mappedBy = "centro")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "centro")
    private List<Reserva> reservas;

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
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreoContacto() {
        return correoContacto;
    }
    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
    public String getSitioWeb() {
        return sitioWeb;
    }
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    public List<Instructor> getInstructores() {
        return instructores;
    }
    public void setInstructores(List<Instructor> instructores) {
        this.instructores = instructores;
    }
    public List<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
}

package com.buceo.inmersion.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_curso")
public class UsuarioCurso {

    @EmbeddedId
    private UsuarioCursoId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private LocalDate fechaInscripcion;
    private Integer estado;

    // Getters y setters
    public UsuarioCursoId getId() {
        return id;
    }
    public void setId(UsuarioCursoId id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}

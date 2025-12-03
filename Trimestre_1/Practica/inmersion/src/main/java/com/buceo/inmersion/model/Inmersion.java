package com.buceo.inmersion.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "inmersion")
public class Inmersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String hora;
    private Integer duracion;
    private Integer profundidadMaxima;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToMany
    @JoinTable(
        name = "sitio_buceo_inmersion",
        joinColumns = @JoinColumn(name = "inmersion_id"),
        inverseJoinColumns = @JoinColumn(name = "sitio_buceo_id")
    )
    private List<SitioBuceo> sitios;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public Integer getProfundidadMaxima() {
        return profundidadMaxima;
    }
    public void setProfundidadMaxima(Integer profundidadMaxima) {
        this.profundidadMaxima = profundidadMaxima;
    }
    public Reserva getReserva() {
        return reserva;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    public List<SitioBuceo> getSitios() {
        return sitios;
    }

    public void setSitios(List<SitioBuceo> sitios) {
        this.sitios = sitios;
    }
    
}

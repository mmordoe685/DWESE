package com.calabozo.mapa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;   // Nombre no puede ser null, se controlará en controlador
    private String clase;    // Guerrero, Mago, Arquero, Pícaro
    private int nivel;       // 1-100, se controlará en controlador
    private int puntosVida;
    private int puntosMana;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private boolean estaVivo;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @OneToOne(mappedBy = "personaje")
    private Inventario inventario;
}

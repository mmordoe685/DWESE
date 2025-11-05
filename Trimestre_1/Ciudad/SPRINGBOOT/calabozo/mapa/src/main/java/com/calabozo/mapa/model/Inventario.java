package com.calabozo.mapa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int capacidad;
    private String apariencia;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personaje_id", referencedColumnName = "id")
    private Personaje personaje;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "inventario_item", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "inventario_id"), // Clave foránea de esta entidad
            inverseJoinColumns = @JoinColumn(name = "item_id") // Clave foránea de la otra entidad
    )
    List<Item> items = new ArrayList<Item>();
}
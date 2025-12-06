package com.calabozo.mapa.repository;

import com.calabozo.mapa.model.Personaje;
import com.calabozo.mapa.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    // Buscar personajes por clase
    List<Personaje> findByClase(String clase);

    // Buscar personajes que estén vivos
    List<Personaje> findByEstaVivoTrue();

    // Buscar personajes por ciudad y clase
    List<Personaje> findByClaseAndCiudad(String clase, Ciudad ciudad);

    // Contar personajes con nivel mayor que X
    long countByNivelGreaterThan(int nivel);

    // Buscar los 3 personajes con mayor nivel
    List<Personaje> findTop3ByOrderByNivelDesc();

    // Buscar personajes por ciudad
    List<Personaje> findByCiudad(Ciudad ciudad);
}

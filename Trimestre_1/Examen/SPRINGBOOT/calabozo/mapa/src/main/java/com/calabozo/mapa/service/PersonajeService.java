package com.calabozo.mapa.service;

import com.calabozo.mapa.model.Personaje;
import com.calabozo.mapa.repository.PersonajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonajeService {

    private final PersonajeRepository personajeRepository;

    public Personaje subirNivel(Long idPersonaje) {
        Personaje p = personajeRepository.findById(idPersonaje)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
        p.setNivel(p.getNivel() + 1);
        p.setPuntosVida(p.getPuntosVida() + 10);
        return personajeRepository.save(p);
    }

    public Personaje curarPersonaje(Long idPersonaje) {
        Personaje p = personajeRepository.findById(idPersonaje)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
        p.setPuntosVida(p.getNivel() * 10);
        return personajeRepository.save(p);
    }

    public Personaje matarPersonaje(Long idPersonaje) {
        Personaje p = personajeRepository.findById(idPersonaje)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
        p.setEstaVivo(false);
        return personajeRepository.save(p);
    }

    public Personaje resucitarPersonaje(Long idPersonaje) {
        Personaje p = personajeRepository.findById(idPersonaje)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
        p.setEstaVivo(true);
        p.setPuntosVida(50);
        return personajeRepository.save(p);
    }

    public Map<String, Object> obtenerEstadisticas() {
        List<Personaje> personajes = personajeRepository.findAll();
        long total = personajes.size();
        long vivos = personajes.stream().filter(Personaje::isEstaVivo).count();
        long muertos = total - vivos;
        double nivelPromedio = personajes.stream().mapToInt(Personaje::getNivel).average().orElse(0);

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("vivos", vivos);
        stats.put("muertos", muertos);
        stats.put("nivelPromedio", nivelPromedio);
        return stats;
    }
}

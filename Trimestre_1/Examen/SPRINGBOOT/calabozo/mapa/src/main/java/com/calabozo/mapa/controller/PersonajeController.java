package com.calabozo.mapa.controller;

import com.calabozo.mapa.model.Ciudad;
import com.calabozo.mapa.model.Personaje;
import com.calabozo.mapa.repository.PersonajeRepository;
import com.calabozo.mapa.repository.CiudadRepository;
import com.calabozo.mapa.service.PersonajeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private PersonajeService personajeService;

    // LISTAR PERSONAJES
    @GetMapping
    public String listarPersonajes(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Personaje> personajes = personajeRepository.findAll();
        model.addAttribute("personajes", personajes);
        model.addAttribute("mensaje", mensaje);
        return "listaPersonajes";
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevo")
    public String nuevoPersonaje(Model model) {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        model.addAttribute("personaje", new Personaje());
        model.addAttribute("ciudades", ciudades);
        return "nuevoPersonaje";
    }

    // CREAR PERSONAJE
    @PostMapping("/crear")
    public String crearPersonaje(@ModelAttribute Personaje personaje, RedirectAttributes ra) {
        try {
            if (personaje.getNombre() == null || personaje.getNombre().isBlank()) {
                ra.addFlashAttribute("mensaje", "Error: El nombre no puede estar vacío");
                return "redirect:/personajes/nuevo";
            }

            if (personaje.getNivel() < 1) personaje.setNivel(1);
            if (personaje.getNivel() > 100) personaje.setNivel(100);

            if (personaje.getCiudad() != null && personaje.getCiudad().getId() != null) {
                Ciudad ciudad = ciudadRepository.findById(personaje.getCiudad().getId())
                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                personaje.setCiudad(ciudad);
            } else {
                personaje.setCiudad(null);
            }

            // Guardar personaje en BD
            personajeRepository.save(personaje);

            ra.addFlashAttribute("mensaje", "Personaje creado correctamente");
            log.info("Personaje creado: {}", personaje.getNombre());

        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al crear personaje: " + e.getMessage());
            return "redirect:/personajes/nuevo";
        }

        return "redirect:/personajes";
    }

    // ELIMINAR PERSONAJE
    @GetMapping("/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable Long id, RedirectAttributes ra) {
        if (personajeRepository.existsById(id)) {
            personajeRepository.deleteById(id);
            ra.addFlashAttribute("mensaje", "Personaje eliminado");
            log.warn("Personaje {} eliminado", id);
        } else {
            ra.addFlashAttribute("mensaje", "Error: Personaje no encontrado");
        }
        return "redirect:/personajes";
    }

    // SUBIR NIVEL
    @GetMapping("/subir-nivel/{id}")
    public String subirNivel(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Personaje p = personajeService.subirNivel(id);
            ra.addFlashAttribute("mensaje", "Nivel de " + p.getNombre() + " subido correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al subir nivel: " + e.getMessage());
        }
        return "redirect:/personajes";
    }

    // CURAR
    @GetMapping("/curar/{id}")
    public String curar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Personaje p = personajeService.curarPersonaje(id);
            ra.addFlashAttribute("mensaje", p.getNombre() + " ha sido curado");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al curar: " + e.getMessage());
        }
        return "redirect:/personajes";
    }

    // MATAR
    @GetMapping("/matar/{id}")
    public String matar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Personaje p = personajeService.matarPersonaje(id);
            ra.addFlashAttribute("mensaje", p.getNombre() + " ha sido eliminado");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al matar: " + e.getMessage());
        }
        return "redirect:/personajes";
    }

    // RESUCITAR
    @GetMapping("/resucitar/{id}")
    public String resucitar(@PathVariable Long id, RedirectAttributes ra) {
        try {
            Personaje p = personajeService.resucitarPersonaje(id);
            ra.addFlashAttribute("mensaje", p.getNombre() + " ha sido resucitado");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al resucitar: " + e.getMessage());
        }
        return "redirect:/personajes";
    }
}

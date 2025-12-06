package com.calabozo.mapa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.calabozo.mapa.model.Ciudad;
import com.calabozo.mapa.model.Personaje;
import com.calabozo.mapa.repository.CiudadRepository;
import com.calabozo.mapa.repository.PersonajeRepository;

@Controller
@RequestMapping("/ciudades")
public class cityController {

    private Logger logger = LoggerFactory.getLogger(cityController.class);

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private PersonajeRepository personajeRepository;

    // LISTADO DE CIUDADES
    @GetMapping
    public String listCities(Model model) {
        List<Ciudad> listaCiudades = ciudadRepository.findAll();
        model.addAttribute("ciudades", listaCiudades);
        return "listaCiudades";
    }

    // ELIMINAR CIUDAD
    @GetMapping("/eliminar/{id}")
    public String removeCity(@PathVariable Long id, RedirectAttributes redAttrib) {
        if (!ciudadRepository.existsById(id)) {
            redAttrib.addFlashAttribute("error", "La ciudad no Existe");
            logger.error("No existe la ciudad");
        } else {
            ciudadRepository.deleteById(id);
            redAttrib.addFlashAttribute("success", "Se ha borrado Correctamente la ciudad con id " + id);
            logger.info("Se ha borrado Correctamente la ciudad con id " + id);
        }
        return "redirect:/ciudades";
    }

    // NUEVA CIUDAD
    @GetMapping("/nuevo")
    public String newCity(Model model) {
        model.addAttribute("ciudad", new Ciudad());
        return "nuevaCiudad";
    }

    @PostMapping("/crear")
    public String createCity(@ModelAttribute("ciudad") Ciudad ciudad) {
        ciudadRepository.save(ciudad);
        logger.info("Se ha creado la ciudad con id " + ciudad.getId());
        return "redirect:/ciudades";
    }

    // EDITAR CIUDAD
    @GetMapping("/editar/{id}")
    public String editCity(@PathVariable Long id, Model model) {
        Ciudad ciudad = ciudadRepository.findById(id).orElse(new Ciudad());
        if (ciudad.getId() == null) {
            model.addAttribute("error", "La ciudad no Existe");
        }
        model.addAttribute("ciudad", ciudad);
        return "editarCiudad";
    }

    @PostMapping("/modificar")
    public String modifyCity(@ModelAttribute("ciudad") Ciudad ciudad, Model model) {
        try {
            ciudadRepository.save(ciudad);
        } catch (Exception e) {
            model.addAttribute("error", "Error al modificar la ciudad");
            return "redirect:/ciudades";
        }
        return "redirect:/ciudades";
    }

    @GetMapping("/{id}/personajes")
    public String personajesPorCiudad(@PathVariable Long id, Model model) {
        Ciudad ciudad = ciudadRepository.findById(id).orElse(null);
        if (ciudad == null) {
            model.addAttribute("mensaje", "Ciudad no encontrada");
            return "redirect:/ciudades";
        }

                List<Personaje> personajes = personajeRepository.findByCiudad(ciudad);

                model.addAttribute("personajes", personajes);
                model.addAttribute("ciudad", ciudad.getNombre());
                return "listaPersonajesPorCiudad"; 
            }
        }

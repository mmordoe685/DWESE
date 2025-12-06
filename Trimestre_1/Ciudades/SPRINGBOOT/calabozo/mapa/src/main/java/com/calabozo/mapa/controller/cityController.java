package com.calabozo.mapa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.calabozo.mapa.model.Ciudad;
import com.calabozo.mapa.repository.CiudadRepository;

@Controller
@RequestMapping("/ciudades")
public class cityController {

    private Logger logger = LoggerFactory.getLogger(cityController.class);

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public String listCities(Model model) {

        List<Ciudad> listaCiudades = ciudadRepository.findAll();

        model.addAttribute("ciudades", listaCiudades);

        return "listaCiudades";
    }

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

    @GetMapping("/nuevo")
    public String newCity(Model model) {

        Ciudad ciudad = new Ciudad();
        model.addAttribute("ciudad", ciudad);
        return "nuevaCiudad";
    }

    @PostMapping("/crear")
    public String createCity(@ModelAttribute("ciudad") Ciudad ciudad) {

        ciudadRepository.save(ciudad);

        logger.info("Se ha creado la ciudad con id " + ciudad.getId());

        return "redirect:/ciudades";
    }

    @GetMapping("/editar/{id}")
    public String editCity(@PathVariable Long id, Model model) {

        Ciudad ciudad = new Ciudad();

        if (!ciudadRepository.existsById(id))
            model.addAttribute("error", "La ciudad no Existe");
        else
            ciudad = ciudadRepository.findById(id).get();

        model.addAttribute("ciudad", ciudad);

        return "editarCiudad";
    }

    @PostMapping("/modificar")
    public String modifyCity(@ModelAttribute("ciudad") Ciudad ciudad, Model model) {

        try {
            ciudadRepository.save(ciudad);

        } catch (Exception e) {
            model.addAttribute("error", "La ciudad no Existe");
            return "redirect:/ciudades";
        }

        return "redirect:/ciudades";

    }


    /*
     * Muestra la información detallada de una ciudad en una tabla.
     * Ruta: /ciudades/info/{id}
     */


    @GetMapping("/info/{id}")
    public String infoCity(@PathVariable Long id, Model model, RedirectAttributes redAttrib) {

        if (!ciudadRepository.existsById(id)) {
            redAttrib.addFlashAttribute("error", "La ciudad no Existe");
            logger.error("Intento de acceso a información de ciudad inexistente ID=" + id);
            return "redirect:/ciudades";
        }

        Ciudad ciudad = ciudadRepository.findById(id).get();

        model.addAttribute("ciudad", ciudad);

        logger.info("Mostrando información de la ciudad con id " + id);

        return "infoCiudad"; 
    }

}

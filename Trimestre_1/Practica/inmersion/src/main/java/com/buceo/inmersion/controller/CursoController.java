package com.buceo.inmersion.controller;

import com.buceo.inmersion.model.Curso;
import com.buceo.inmersion.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Mostrar lista de cursos
    @GetMapping("/lista")
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "ListaCursos";
    }

    // Mostrar formulario para crear nuevo curso
    @GetMapping("/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "FormularioCurso";
    }

    // Guardar curso creado o editado
    @PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoRepository.save(curso);
        return "redirect:/cursos/lista";
    }

    // Mostrar formulario para editar curso
    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoRepository.findById(id).orElseThrow();
        model.addAttribute("curso", curso);
        return "FormularioCurso";
    }

    // Eliminar curso
    @GetMapping("/borrar/{id}")
    public String borrarCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return "redirect:/cursos/lista";
    }
}

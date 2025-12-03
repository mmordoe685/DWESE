package com.buceo.inmersion.API;

import com.buceo.inmersion.model.Curso;
import com.buceo.inmersion.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class ApiCursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll(Sort.by(Sort.Order.asc("nombre")));
    }

    // Listar cursos paginados
    @GetMapping("/paginado")
    public Page<Curso> getCursosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        return cursoRepository.findAll(pageable);
    }

    // Crear curso
    @PostMapping("/crear")
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoRecibido) {
        Optional<Curso> cursoBD = cursoRepository.findById(id);
        if (!cursoBD.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Curso curso = cursoBD.get();
        curso.setNombre(cursoRecibido.getNombre());
        curso.setNivel(cursoRecibido.getNivel());
        curso.setPrecio(cursoRecibido.getPrecio());
        curso.setDuracion(cursoRecibido.getDuracion());
        curso.setDescripcion(cursoRecibido.getDescripcion());
        curso.setCentro(cursoRecibido.getCentro());

        cursoRepository.save(curso);
        return ResponseEntity.ok(curso);
    }

    // Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    // Borrar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        if (!cursoRepository.existsById(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Buscar cursos por nombre
    @GetMapping("/buscar")
    public List<Curso> buscarCursos(@RequestParam String nombre) {
        return cursoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

package com.buceo.inmersion.API;

import com.buceo.inmersion.model.Usuario;
import com.buceo.inmersion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class ApiUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll(Sort.by(Sort.Order.asc("apellido"), Sort.Order.asc("nombre")));
    }

    // Listar usuarios paginados
    @GetMapping("/paginado")
    public Page<Usuario> getUsuariosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("apellido").ascending());
        return usuarioRepository.findAll(pageable);
    }

    // Crear usuario
    @PostMapping("/crear")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRecibido) {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if (!usuarioBD.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Usuario usuario = usuarioBD.get();
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setApellido(usuarioRecibido.getApellido());
        usuario.setCorreoElectronico(usuarioRecibido.getCorreoElectronico());
        usuario.setContrasena(usuarioRecibido.getContrasena());
        usuario.setRol(usuarioRecibido.getRol());

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    // Borrar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Buscar usuarios por nombre
    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(@RequestParam String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

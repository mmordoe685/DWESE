package com.buceo.inmersion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.buceo.inmersion.model.Usuario;
import com.buceo.inmersion.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }
}

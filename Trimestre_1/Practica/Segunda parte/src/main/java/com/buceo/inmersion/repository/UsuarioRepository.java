package com.buceo.inmersion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buceo.inmersion.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}

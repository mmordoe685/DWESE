package com.buceo.inmersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buceo.inmersion.model.UsuarioCurso;
import com.buceo.inmersion.model.UsuarioCursoId;

public interface UsuarioCursoRepository extends JpaRepository<UsuarioCurso, UsuarioCursoId> {}

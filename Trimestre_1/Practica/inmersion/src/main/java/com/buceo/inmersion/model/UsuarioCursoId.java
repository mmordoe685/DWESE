package com.buceo.inmersion.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioCursoId implements Serializable {

    private Long usuarioId;
    private Long cursoId;

    public UsuarioCursoId() {}

    public UsuarioCursoId(Long usuarioId, Long cursoId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
    }

    // Getters y setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioCursoId)) return false;
        UsuarioCursoId that = (UsuarioCursoId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(cursoId, that.cursoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, cursoId);
    }
}

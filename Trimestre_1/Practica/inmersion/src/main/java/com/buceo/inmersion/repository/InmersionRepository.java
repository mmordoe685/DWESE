package com.buceo.inmersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buceo.inmersion.model.Inmersion;

public interface InmersionRepository extends JpaRepository<Inmersion, Long> {}

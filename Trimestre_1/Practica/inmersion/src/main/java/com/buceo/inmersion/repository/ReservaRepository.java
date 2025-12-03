package com.buceo.inmersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buceo.inmersion.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}

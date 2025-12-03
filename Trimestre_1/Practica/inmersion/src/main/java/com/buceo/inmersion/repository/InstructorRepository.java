package com.buceo.inmersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buceo.inmersion.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {}

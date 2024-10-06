package org.example.persistencia.repository;

import org.example.persistencia.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

}

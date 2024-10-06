package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Conductor> findAllByNombre(String text);

    List<Conductor> findAllByNombreStartingWith(String text);

    List<Conductor> findAllByNombreStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:text, '%')")
    List<Conductor> findPersonsByNameStartingWith(@Param("text") String text);

    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:text,'%'))")
    List<Conductor> findPersonsByNameStartingWithCaseInsensitive(@Param("text") String text);

}

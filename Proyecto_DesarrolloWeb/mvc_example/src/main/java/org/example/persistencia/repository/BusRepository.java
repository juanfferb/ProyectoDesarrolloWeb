package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Bus> findAllByNombre(String text);

    List<Bus> findAllByNombreStartingWith(String text);

    List<Bus> findAllByNombreStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:text, '%')")
    List<Bus> findPersonsByNameStartingWith(@Param("text") String text);

    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:text,'%'))")
    List<Bus> findPersonsByNameStartingWithCaseInsensitive(@Param("text") String text);
}

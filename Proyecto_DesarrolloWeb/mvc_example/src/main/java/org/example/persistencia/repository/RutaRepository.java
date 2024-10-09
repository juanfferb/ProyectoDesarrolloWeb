package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
    // https://www.baeldung.com/spring-data-derived-queries
    List<Ruta> findAllByNombre(String text);

    List<Ruta> findAllByNombreStartingWith(String text);

    List<Ruta> findAllByNombreStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:text, '%')")
    List<Ruta> findPersonsByNameStartingWith(@Param("text") String text);

    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:text,'%'))")
    List<Ruta> findPersonsByNameStartingWithCaseInsensitive(@Param("text") String text);
}

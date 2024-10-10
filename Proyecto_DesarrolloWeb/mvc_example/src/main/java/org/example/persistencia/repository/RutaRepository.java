package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
    // https://www.baeldung.com/spring-data-derived-queries
    List<RutaDTO> findAllByCodigo(String text);

    List<RutaDTO> findAllByCodigoStartingWith(String text);

    List<RutaDTO> findAllByCodigoStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Ruta c WHERE c.codigo LIKE concat(:text, '%')")
    List<Ruta> findRutasByCodigoStartingWith(@Param("text") String text);

    @Query("SELECT c FROM Ruta c WHERE LOWER(c.codigo) LIKE LOWER(concat(:text,'%'))")
    List<Ruta> findRutasByCodigoStartingWithCaseInsensitive(@Param("text") String text);
}

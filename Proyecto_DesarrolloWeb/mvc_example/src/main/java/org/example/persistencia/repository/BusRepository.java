package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<BusDTO> findAllByModelo(String text);

    List<BusDTO> findAllByModeloStartingWith(String text);

    List<BusDTO> findAllByModeloStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT c FROM Bus c WHERE c.modelo LIKE concat(:text, '%')")
    List<Bus> findBusByModeloStartingWith(@Param("text") String text);

    @Query("SELECT c FROM Bus c WHERE LOWER(c.modelo) LIKE LOWER(concat(:text,'%'))")
    List<Bus> findBusByModeloStartingWithCaseInsensitive(@Param("text") String text);
}

package org.example.persistencia.repository;

import org.example.persistencia.model.Asignacion;
import org.example.persistencia.dto.AsignacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    // Método para encontrar asignaciones por ID de conductor
    @Query("SELECT a FROM Asignacion a WHERE a.conductor.id = :conductorId")
    List<Asignacion> findAsignacionesByConductorId(@Param("conductorId") Long conductorId);

    // Método para encontrar asignaciones por ID de conductor
    @Query("SELECT a FROM Asignacion a WHERE a.bus.id = :busId")
    List<Asignacion> findAsignacionesByBusId(@Param("busId") Long busId);

    // Método para encontrar asignaciones por ID de conductor
    @Query("SELECT a FROM Asignacion a WHERE a.ruta.id = :rutaId")
    List<Asignacion> findAsignacionesByRutaId(@Param("rutaId") Long rutaId);

}

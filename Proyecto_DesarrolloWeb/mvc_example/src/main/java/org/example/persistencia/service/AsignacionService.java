package org.example.persistencia.service;

import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Asignacion> getAsignacionesByConductorId(Long conductorId) {
        return asignacionRepository.findAsignacionesByConductorId(conductorId);
    }

    public Asignacion crearAsignacion(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public boolean existeConflictoDias(Asignacion asignacion) {
        // Implementar la lógica para verificar si los días de la nueva asignación
        // se cruzan con otra asignación para el mismo conductor
        return false; // Ejemplo, implementar la lógica real
    }
    
    public boolean existeConflictoBus(Asignacion asignacion) {
        // Implementar la lógica para verificar si el bus ya está asignado en los mismos días
        return false; // Ejemplo, implementar la lógica real
    }

    public void eliminarAsignacion(Long id) {
        Asignacion asignacion = asignacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asignación no encontrada con ID: " + id));
        asignacionRepository.delete(asignacion);
    }

    public Asignacion getAsignacionById(Long id) {
    return asignacionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Asignación no encontrada con ID: " + id));
}
    
}

package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.ConductorRepository;
import org.example.persistencia.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Conductor> listarconductores() {
        return conductorRepository.findAll();
    }

    public Conductor recuperarConductor(Long id) {
        return conductorRepository.findById(id).orElseThrow();
    }

    public void guardarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    public List<Conductor> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findAllByNombreStartingWith(textoBusqueda);
    }

    // Nuevo método para crear un conductor
    public Conductor crearConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    public void eliminarConductor(Long id) {
        // Primero eliminamos las asignaciones asociadas al conductor
        List<Asignacion> asignaciones = asignacionRepository.findAsignacionesByConductorId(id);
        for (Asignacion asignacion : asignaciones) {
            asignacionRepository.delete(asignacion);
        }

        // Luego eliminamos el conductor
        Conductor conductor = recuperarConductor(id);
        conductorRepository.delete(conductor);
    }
}

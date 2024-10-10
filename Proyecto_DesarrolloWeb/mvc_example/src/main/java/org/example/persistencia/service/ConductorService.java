package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.conversion.ConductorDTOConverter;
import org.example.persistencia.dto.ConductorDTO;
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
    @Autowired
    private ConductorDTOConverter conductorDTOConverter;


    public List<ConductorDTO> listarconductores() {
        List<Conductor> conductores = conductorRepository.findAll();
        return conductorDTOConverter.entityToDTO(conductores); 
    }

    public ConductorDTO recuperarConductor(Long id) {
        return conductorDTOConverter.entityToDTO(conductorRepository.findById(id).orElseThrow());
    }

    public void guardarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    /*public List<ConductorDTO> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findAllByNombreStartingWith(textoBusqueda);
    }*/

    public List<ConductorDTO> buscarPorNombre(String textoBusqueda) {
        List<Conductor> conductores = conductorRepository.findPersonsByNameStartingWith(textoBusqueda);
        return conductorDTOConverter.entityToDTO(conductores);
    }
    
    
    

    // Nuevo método para crear un conductor
    public ConductorDTO crearConductor(ConductorDTO conductorDTO) {
        Conductor conductor = conductorDTOConverter.DTOToEntity(conductorDTO);
        return conductorDTOConverter.entityToDTO(conductorRepository.save(conductor));
    }

    public void eliminarConductor(Long id) {
        // Primero eliminamos las asignaciones asociadas al conductor
        List<Asignacion> asignaciones = asignacionRepository.findAsignacionesByConductorId(id);
        for (Asignacion asignacion : asignaciones) {
            asignacionRepository.delete(asignacion);
        }
    
        // Luego eliminamos el conductor
        Conductor conductor = conductorRepository.findById(id).orElseThrow();
        conductorRepository.delete(conductor);  // Este código estaba comentado
    }
    
}

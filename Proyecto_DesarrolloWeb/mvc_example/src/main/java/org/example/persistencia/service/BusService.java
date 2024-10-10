package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.conversion.BusDTOConverter;
import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Asignacion;
import org.example.persistencia.repository.BusRepository;
import org.example.persistencia.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.persistencia.repository.AsignacionRepository;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private AsignacionRepository asignacionRepository;
    @Autowired
    private BusDTOConverter busDTOConverter;

    public List<Bus> listarbuses() {
        return busRepository.findAll();
    }

    public BusDTO recuperarBus(Long id) {
        return busDTOConverter.entityToDTO(busRepository.findById(id).orElseThrow());
    }

    public void guardarBus(Bus bus) {
        busRepository.save(bus);
    }

    public List<Bus> buscarPorNombre(String textoBusqueda) {
        return busRepository.findAllByNombreStartingWith(textoBusqueda);
    }

    // Nuevo método para crear un conductor
    public BusDTO crearBus(BusDTO busDTO) {
        Bus bus = busDTOConverter.DTOToEntity(busDTO);
        return busDTOConverter.entityToDTO(busRepository.save(bus));
    }

    public void eliminarBus(Long id) {
        /*
        // Primero eliminamos las asignaciones asociadas al conductor
        List<Asignacion> asignaciones = asignacionRepository.findAsignacionesByConductorId(id);
        for (Asignacion asignacion : asignaciones) {
            asignacionRepository.delete(asignacion);
        }

        // Luego eliminamos el conductor
        BusDTO busDTO = recuperarConductor(id);
        //conductorRepository.delete(conductorDTO);*/
    }
}

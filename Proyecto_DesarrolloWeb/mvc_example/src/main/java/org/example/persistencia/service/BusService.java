package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.conversion.BusDTOConverter;
import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.BusRepository;
import org.example.persistencia.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    @Autowired
    private BusRepository BusRepository;
    @Autowired
    private AsignacionRepository asignacionRepository;
    @Autowired
    private BusDTOConverter BusDTOConverter;


    public List<BusDTO> listarBuses() {
        List<Bus> Buses = BusRepository.findAll();
        return BusDTOConverter.entityToDTO(Buses); 
    }

    public BusDTO recuperarBus(Long id) {
        return BusDTOConverter.entityToDTO(BusRepository.findById(id).orElseThrow());
    }

    public void guardarBus(Bus Bus) {
        BusRepository.save(Bus);
    }

    public List<BusDTO> buscarPorModelo(String textoBusqueda) {
        return BusRepository.findAllByModeloStartingWith(textoBusqueda);
    }

    // Nuevo método para crear un Bus
    public BusDTO crearBus(BusDTO BusDTO) {
        Bus Bus = BusDTOConverter.DTOToEntity(BusDTO);
        return BusDTOConverter.entityToDTO(BusRepository.save(Bus));
    }

    public BusDTO actualizarBus(Long id, BusDTO BusDTO) {
        Bus bus = BusRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        bus.setModelo(BusDTO.getModelo());
        bus.setPlaca(BusDTO.getPlaca());
        BusRepository.save(bus);
        return BusDTOConverter.entityToDTO(bus);
    }  

    public void eliminarBus(Long id) {
        // Primero eliminamos las asignaciones asociadas al conductor
        List<Asignacion> asignaciones = asignacionRepository.findAsignacionesByBusId(id);
        for (Asignacion asignacion : asignaciones) {
            asignacionRepository.delete(asignacion);
        }
    
        // Luego eliminamos el conductor
        Bus bus = BusRepository.findById(id).orElseThrow();
        BusRepository.delete(bus);  // Este código estaba comentado
    }
}

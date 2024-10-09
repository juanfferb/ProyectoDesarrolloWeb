package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.conversion.BusDTOConverter;
import org.example.persistencia.model.Bus;
import org.example.persistencia.repository.BusRepository;
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
}

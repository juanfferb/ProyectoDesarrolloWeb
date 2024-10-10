package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.model.Bus;

@Component
public class BusDTOConverter {
    public BusDTO entityToDTO(Bus Bus) {
        return new BusDTO(Bus.getId(), Bus.getPlaca(), Bus.getModelo(), Bus.getConductoresAsignados(), Bus.getAsignaciones());
    }
 
    public Bus DTOToEntity(BusDTO Bus) {
        return new Bus(Bus.getPlaca(), Bus.getModelo());
    }
    public List<BusDTO> entityToDTO(List<Bus> buses) {
        return buses.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
}

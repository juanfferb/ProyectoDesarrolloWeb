package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.model.Bus;

@Component
public class BusDTOConverter {
    public BusDTO entityToDTO(Bus Bus) {
        return new BusDTO(Bus.getId(), Bus.getPlaca(), Bus.getModelo());
    }
 
    public Bus DTOToEntity(BusDTO Bus) {
        return new Bus(Bus.getPlaca(), Bus.getModelo());
 
    }
}

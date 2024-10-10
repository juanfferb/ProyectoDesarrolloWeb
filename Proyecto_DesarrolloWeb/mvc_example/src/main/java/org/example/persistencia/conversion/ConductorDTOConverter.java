package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.model.Conductor;

@Component
public class ConductorDTOConverter {

    public ConductorDTO entityToDTO(Conductor list) {
        return new ConductorDTO(list.getId(), list.getNombre(), list.getCedula(), list.getTelefono(), list.getDireccion(), list.getBusesAsignados());
    }
 
    public Conductor DTOToEntity(ConductorDTO Conductor) {
        return new Conductor(Conductor.getNombre(), Conductor.getCedula(), Conductor.getTelefono(), Conductor.getDireccion());
 
    }

    public List<ConductorDTO> entityToDTO(List<Conductor> conductores) {
        return conductores.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    
}

package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.model.Conductor;

@Component
public class ConductorDTOConverter {

    public ConductorDTO entityToDTO(Conductor Conductor) {
        return new ConductorDTO(Conductor.getId(), Conductor.getNombre(), Conductor.getCedula(), Conductor.getTelefono(), Conductor.getDireccion(), Conductor.getBusesAsignados());
    }
 
    public Conductor DTOToEntity(ConductorDTO Conductor) {
        return new Conductor(Conductor.getNombre(), Conductor.getCedula(), Conductor.getTelefono(), Conductor.getDireccion());
 
    }
    
}

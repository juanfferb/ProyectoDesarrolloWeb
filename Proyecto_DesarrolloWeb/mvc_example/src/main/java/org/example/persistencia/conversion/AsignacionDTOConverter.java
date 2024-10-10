package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import org.example.persistencia.dto.AsignacionDTO;
import org.example.persistencia.model.Asignacion;

@Component
public class AsignacionDTOConverter {
    public AsignacionDTO entityToDTO(Asignacion Asignacion) {
        return new AsignacionDTO(Asignacion.getId(), Asignacion.getConductor(), Asignacion.getBus(), Asignacion.getRuta(), Asignacion.getDiasAsignacion());
    }
 
    public Asignacion DTOToEntity(AsignacionDTO Asignacion) {
        return new Asignacion(Asignacion.getConductor(), Asignacion.getBus(), Asignacion.getRuta(), Asignacion.getDiasAsignacion());
    }
    public List<AsignacionDTO> entityToDTO(List<Asignacion> Asignaciones) {
        return Asignaciones.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
}

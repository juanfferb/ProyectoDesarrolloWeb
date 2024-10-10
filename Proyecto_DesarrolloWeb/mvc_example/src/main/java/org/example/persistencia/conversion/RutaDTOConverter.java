package org.example.persistencia.conversion;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Ruta;

@Component
public class RutaDTOConverter {
    public RutaDTO entityToDTO(Ruta Ruta) {
        return new RutaDTO(Ruta.getId(), Ruta.getCodigo(), Ruta.getEstaciones(), Ruta.getHorario(), Ruta.getAsignaciones());
    }
 
    public Ruta DTOToEntity(RutaDTO Ruta) {
        return new Ruta(Ruta.getCodigo(), Ruta.getEstaciones(), Ruta.getHorario());
    }
    public List<RutaDTO> entityToDTO(List<Ruta> rutas) {
        return rutas.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
}

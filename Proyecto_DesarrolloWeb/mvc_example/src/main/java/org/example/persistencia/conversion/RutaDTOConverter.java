package org.example.persistencia.conversion;

import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Ruta;

public class RutaDTOConverter {
    public RutaDTO entityToDTO(Ruta Ruta) {
        return new RutaDTO(Ruta.getId(), Ruta.getCodigo(), Ruta.getEstaciones(), Ruta.getHorario(), Ruta.getAsignaciones());
    }
 
    public Ruta DTOToEntity(RutaDTO Ruta) {
        return new Ruta(Ruta.getCodigo(), Ruta.getEstaciones(), Ruta.getHorario(), Ruta.getAsignaciones());
    }
}

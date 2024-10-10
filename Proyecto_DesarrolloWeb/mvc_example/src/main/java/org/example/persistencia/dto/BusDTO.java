package org.example.persistencia.dto;

import java.util.List;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.model.Asignacion;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor

public class BusDTO {
    
    private Long id;
    private String placa;
    private String modelo;
    private List<Conductor> conductores;
    private List<Asignacion> asignaciones;

}

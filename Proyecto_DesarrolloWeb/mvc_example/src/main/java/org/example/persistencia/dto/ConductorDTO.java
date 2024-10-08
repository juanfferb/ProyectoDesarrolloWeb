package org.example.persistencia.dto;

import java.util.List;

import org.example.persistencia.model.Bus;

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
public class ConductorDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private List<Bus> busesAsignados;
 
}

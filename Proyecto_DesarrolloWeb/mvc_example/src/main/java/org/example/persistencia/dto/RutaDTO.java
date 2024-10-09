package org.example.persistencia.dto;

import java.util.List;

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

public class RutaDTO {

    private Long id;
    private String nombre;
    private List<Asignacion> busesAsignados;

}

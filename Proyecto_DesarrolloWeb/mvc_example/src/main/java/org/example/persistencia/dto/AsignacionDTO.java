package org.example.persistencia.dto;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.model.Ruta;

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
public class AsignacionDTO {

    private Long id;
    private Conductor conductor;
    private Bus bus;
    private Ruta ruta;
    private String diasAsignacion;
 
}

package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.dto.AsignacionDTO;
import org.example.persistencia.service.AsignacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asignacion")
public class AsignacionController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AsignacionService asignacionService;

    @GetMapping("/list/{conductorId}")
    public List<AsignacionDTO> listarAsignacionesByConductor(@PathVariable("conductorId") Long conductorId) {
        return asignacionService.getAsignacionesByConductorId(conductorId);
    }

    @GetMapping("/view/{id}")
    public AsignacionDTO verAsignacion(@PathVariable("id") Long id) {
        return asignacionService.getAsignacionById(id);
    }

    @PostMapping("/create")
    public AsignacionDTO crearAsignacion(@Valid @RequestBody AsignacionDTO asignacionDTO) {
        return asignacionService.crearAsignacion(asignacionDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarAsignacion(@PathVariable("id") Long id) {
        asignacionService.eliminarAsignacion(id);
    }
}

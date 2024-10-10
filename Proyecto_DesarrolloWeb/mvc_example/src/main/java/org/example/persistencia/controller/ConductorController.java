package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.service.ConductorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conductor")
public class ConductorController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/list")
    public List<ConductorDTO> listarConductores() {
        return conductorService.listarconductores();
    }

    @GetMapping("/view/{id}")
    public ConductorDTO verConductor(@PathVariable("id") Long id) {
        return conductorService.recuperarConductor(id);
    }

    @PutMapping("/update/{id}")
    public ConductorDTO actualizarConductor(@PathVariable Long id, @Valid @RequestBody ConductorDTO conductorDTO) {
    return conductorService.actualizarConductor(id, conductorDTO);
    }

    @PostMapping("/save")
    public void guardarConductor(@Valid @RequestBody Conductor conductor) {
        conductorService.guardarConductor(conductor);
    }

    @PostMapping("/create")
    public void crearConductor(@Valid @RequestBody ConductorDTO conductorDTO) {
        conductorService.crearConductor(conductorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarConductor(@PathVariable("id") Long id) {
        conductorService.eliminarConductor(id);
    }

    @GetMapping("/search")
    public List<ConductorDTO> buscarConductores(@RequestParam(required = false) String nombre) {
        System.out.println(nombre);
        if (nombre == null || nombre.trim().isEmpty()) {
            log.info("No hay texto de búsqueda. Retornando todos los conductores");
            return conductorService.listarconductores();
        } else {
            log.info("Buscando conductores cuyo nombre contiene {}", nombre);
            return conductorService.buscarPorNombre(nombre);
        }
    }
}

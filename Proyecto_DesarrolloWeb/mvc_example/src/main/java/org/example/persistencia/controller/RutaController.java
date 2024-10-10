package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Ruta;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.service.RutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/*
import org.example.persistence.model.Ruta;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.service.RutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
*/


@RestController
public class RutaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public List<Ruta> listarRutas() {
        List<Ruta> rutas = rutaService.listarrutas();
        return rutas;
    }

    @GetMapping("/view/{id}")
    public RutaDTO verRuta(@PathVariable("id") Long id) {
        RutaDTO ruta = rutaService.recuperarRuta(id);
        return ruta;
    
    }

    @PostMapping("/save")
    public void guardarRuta(@Valid @RequestBody RutaDTO RutaDTO) {
        RutaService.guardarRuta(RutaDTO);
    }

    @PostMapping("/create")
    public void crearRuta(@Valid @RequestBody RutaDTO RutaDTO) {
        RutaService.crearRuta(RutaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarRuta(@PathVariable("id") Long id) {
        RutaService.eliminarRuta(id);
    }

    @GetMapping("/search")
    public List<RutaDTO> buscarRutas(@RequestParam(required = false) String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            log.info("No hay texto de búsqueda. Retornando todos los Rutaes");
            return RutaService.listarRutas();
        } else {
            log.info("Rutacando Rutaes cuyo nombre contiene {}", searchText);
            return RutaService.buscarPorNombre(searchText);
        }
    }
}

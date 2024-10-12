package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.service.RutaService;
import org.example.persistencia.service.RutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Ruta")
public class RutaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RutaService RutaService;

    @GetMapping("/list")
    public List<RutaDTO> listarRutaes() {
        return RutaService.listarRutas();
    }

    @GetMapping("/view/{id}")
    public RutaDTO verRuta(@PathVariable("id") Long id) {
        return RutaService.recuperarRuta(id);
    }

    @PostMapping("/save")
    public void guardarRuta(@Valid @RequestBody Ruta Ruta) {
        RutaService.guardarRuta(Ruta);
    }

    @PostMapping("/create")
    public void crearRuta(@Valid @RequestBody RutaDTO RutaDTO) {
        RutaService.crearRuta(RutaDTO);
    }

    @PutMapping("/update/{id}")
    public RutaDTO actualizarRuta(@PathVariable Long id, @Valid @RequestBody RutaDTO RutaDTO) {
    return RutaService.actualizarRuta(id, RutaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarRuta(@PathVariable("id") Long id) {
        RutaService.eliminarRuta(id);
    }

    @GetMapping("/search")
    public List<RutaDTO> RutacarRutas(@RequestParam(required = false) String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            log.info("No hay texto de búsqueda. Retornando todos los Rutaes");
            return RutaService.listarRutas();
        } else {
            log.info("Rutacando Rutaes cuyo nombre contiene {}", searchText);
            return RutaService.RutacarPorCodigo(searchText);
        }
    }
}

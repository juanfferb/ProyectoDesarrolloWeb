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
import org.springframework.security.access.annotation.Secured;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Ruta")
public class RutaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RutaService RutaService;

    @Secured({ "USER", "ADMIN" })
    @GetMapping("/list")
    public List<RutaDTO> listarRutaes() {
        return RutaService.listarRutas();
    }

    @Secured({ "USER", "ADMIN" })
    @GetMapping("/view/{id}")
    public RutaDTO verRuta(@PathVariable("id") Long id) {
        return RutaService.recuperarRuta(id);
    }

    @Secured({ "ADMIN" })
    @PostMapping("/save")
    public void guardarRuta(@Valid @RequestBody Ruta Ruta) {
        RutaService.guardarRuta(Ruta);
    }

    @Secured({ "ADMIN" })
    @PostMapping("/create")
    public void crearRuta(@Valid @RequestBody RutaDTO RutaDTO) {
        RutaService.crearRuta(RutaDTO);
    }

    @Secured({ "ADMIN" })
    @PutMapping("/update/{id}")
    public RutaDTO actualizarRuta(@PathVariable Long id, @Valid @RequestBody RutaDTO RutaDTO) {
    return RutaService.actualizarRuta(id, RutaDTO);
    }

    @Secured({ "ADMIN" })
    @DeleteMapping("/delete/{id}")
    public void eliminarRuta(@PathVariable("id") Long id) {
        RutaService.eliminarRuta(id);
    }

    @Secured({ "USER", "ADMIN" })
    @GetMapping("/search")
    public List<RutaDTO> buscarRutas(@RequestParam(required = false) String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            log.info("No hay texto de búsqueda. Retornando todos las Rutas");
            return RutaService.listarRutas();
        } else {
            log.info("Buscando rutas cuyo nombre contiene {}", searchText);
            return RutaService.buscarPorCodigo(searchText);
        }
    }
}

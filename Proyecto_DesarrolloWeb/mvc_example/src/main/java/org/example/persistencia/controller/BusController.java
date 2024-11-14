package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.conversion.BusDTOConverter;
import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.model.Bus;
import org.example.persistencia.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Bus")
public class BusController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusService BusService;

    @Secured({ "COORD" })
    @GetMapping("/list")
    public List<BusDTO> listarBuses() {
        return BusService.listarBuses();
    }

    @Secured({ "COORD" })
    @GetMapping("/view/{id}")
    public BusDTO verBus(@PathVariable("id") Long id) {
        return BusService.recuperarBus(id);
    }

    @Secured({ "COORD" })
    @PostMapping("/save")
    public void guardarBus(@Valid @RequestBody Bus Bus) {
        BusService.guardarBus(Bus);
    }

    @Secured({ "COORD" })
    @PostMapping("/create")
    public void crearBus(@Valid @RequestBody BusDTO BusDTO) {
        BusService.crearBus(BusDTO);
    }

    @Secured({ "COORD" })
    @PutMapping("/update/{id}")
    public BusDTO actualizarBus(@PathVariable Long id, @Valid @RequestBody BusDTO busDTO) {
    return BusService.actualizarBus(id, busDTO);
    }

    @Secured({ "COORD" })
    @DeleteMapping("/delete/{id}")
    public void eliminarBus(@PathVariable("id") Long id) {
        BusService.eliminarBus(id);
    }

    @Secured({ "COORD" })
    @GetMapping("/search")
    public List<BusDTO> buscarBuses(@RequestParam(required = false) String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            log.info("No hay texto de búsqueda. Retornando todos los Buses");
            return BusService.listarBuses();
        } else {
            log.info("Buscando Buses cuyo nombre contiene {}", searchText);
            return BusService.buscarPorModelo(searchText);
        }
    }
}

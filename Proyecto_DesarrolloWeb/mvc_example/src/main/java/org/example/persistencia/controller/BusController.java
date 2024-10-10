package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.model.Bus;
import org.example.persistencia.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Bus")
public class BusController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusService BusService;

    @GetMapping("/list")
    public List<BusDTO> listarBuses() {
        return BusService.listarBuses();
    }

    @GetMapping("/view/{id}")
    public BusDTO verBus(@PathVariable("id") Long id) {
        return BusService.recuperarBus(id);
    }

    @PostMapping("/save")
    public void guardarBus(@Valid @RequestBody Bus Bus) {
        BusService.guardarBus(Bus);
    }

    @PostMapping("/create")
    public void crearBus(@Valid @RequestBody BusDTO BusDTO) {
        BusService.crearBus(BusDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarBus(@PathVariable("id") Long id) {
        BusService.eliminarBus(id);
    }

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

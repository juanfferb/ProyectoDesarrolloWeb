package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bus")
public class BusController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusService busService;

    @GetMapping("/list")
    public List<Bus> listarBuses() {
        List<Bus> buses = busService.listarbuses();
        return buses;
    }

    @GetMapping("/view/{id}")
    public BusDTO verBus(@PathVariable("id") Long id) {
        BusDTO bus = busService.recuperarBus(id);
        return bus;
    }

    @PostMapping("/save")
    public void guardarBus(@Valid @RequestBody BusDTO BusDTO) {
        BusService.guardarBus(BusDTO);
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
            return BusService.buscarPorNombre(searchText);
        }
    }

}

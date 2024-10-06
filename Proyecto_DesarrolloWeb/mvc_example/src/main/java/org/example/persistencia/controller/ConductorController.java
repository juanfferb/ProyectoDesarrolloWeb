package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Conductor;
import org.example.persistencia.service.ConductorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/conductor")
public class ConductorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/list")
    public ModelAndView listarConductores() {
        List<Conductor> conductores = conductorService.listarconductores();
        ModelAndView modelAndView = new ModelAndView("conductor-list");
        modelAndView.addObject("conductores", conductores);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView verConductor(@PathVariable("id") Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("conductor-view");
        modelAndView.addObject("conductor", conductor);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarConductor(@PathVariable Long id) {
        Conductor c = conductorService.recuperarConductor(id);
        ModelAndView modelAndView = new ModelAndView("conductor-edit");
        modelAndView.addObject("conductor", c);
        return modelAndView;
    }

    @PostMapping(value = "/save")
    public Object guardarConductor(@Valid Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("conductor-edit");
        }
        conductorService.guardarConductor(conductor);
        return new RedirectView("/conductor/list");
    }

    @GetMapping(value = "/create")
    public Object nuevoConductor(@Valid Conductor conductor, BindingResult result) {
    if (result.hasErrors()) {
        return new ModelAndView("conductor-create");
    }
    conductorService.crearConductor(conductor);
    return new RedirectView("/conductor/list");
    }

    @PostMapping(value = "/create")
    public Object crearConductor(@Valid Conductor conductor, BindingResult result) {
    if (result.hasErrors()) {
        return new ModelAndView("conductor-create");
    }
    conductorService.crearConductor(conductor);
    return new RedirectView("/conductor/list");
    }

    @PostMapping("/delete/{id}")
    public RedirectView eliminarConductor(@PathVariable("id") Long id) {
        conductorService.eliminarConductor(id);
        return new RedirectView("/conductor/list");
    }

    @GetMapping("/search")
    public ModelAndView buscarConductores(@RequestParam(required = false) String searchText) {
        List<Conductor> conductores;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            conductores = conductorService.listarconductores();
        } else {
            log.info("Buscando conductores cuyo nombre contiene {}", searchText);
            conductores = conductorService.buscarPorNombre(searchText);
        }
        ModelAndView modelAndView = new ModelAndView("conductor-search");
        modelAndView.addObject("conductores", conductores);
        return modelAndView;
    }


}

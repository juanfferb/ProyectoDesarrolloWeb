package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.dto.BusDTO;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.service.BusService;
import org.example.persistencia.service.RutaService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;


@RestController
public class RutaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public List<Ruta> listarRutas() {
        List<Ruta> rutas = rutaService.listarrutas();
        //ModelAndView modelAndView = new ModelAndView("bus-list");
        //modelAndView.addObject("buses", buses);
        return rutas;
    }

    @GetMapping("/view/{id}")
    public RutaDTO verBus(@PathVariable("id") Long id) {
        RutaDTO ruta = rutaService.recuperarRuta(id);
        ModelAndView modelAndView = new ModelAndView("bus-view");
        modelAndView.addObject("ruta", ruta);
        return ruta;
    
    }
}

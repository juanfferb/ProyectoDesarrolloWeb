package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.example.persistencia.service.BusService;
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
@RequestMapping("/bus")
public class BusController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusService busService;

    @GetMapping("/list")
    public ModelAndView listarBuses() {
        List<Bus> buses = busService.listarbuses();
        ModelAndView modelAndView = new ModelAndView("bus-list");
        modelAndView.addObject("buses", buses);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView verBus(@PathVariable("id") Long id) {
        Bus bus = busService.recuperarBus(id);
        ModelAndView modelAndView = new ModelAndView("bus-view");
        modelAndView.addObject("bus", bus);
        return modelAndView;
    }

}

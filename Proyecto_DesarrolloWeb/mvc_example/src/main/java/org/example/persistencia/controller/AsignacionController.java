package org.example.persistencia.controller;

import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.service.AsignacionService;
import org.example.persistencia.service.BusService;
import org.example.persistencia.service.ConductorService;
import org.example.persistencia.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/asignacion")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private BusService busService;

    @Autowired
    private RutaService rutaService;

    @GetMapping("/view/{id}")
    public String viewAsignacionesByConductor(@PathVariable("id") Long conductorId, Model model) {
        List<Asignacion> asignaciones = asignacionService.getAsignacionesByConductorId(conductorId);
        model.addAttribute("asignaciones", asignaciones);
        return "asignacion-view";
    }

    
    @GetMapping(value = "/create")

     public ModelAndView nuevoConductor() {

         ModelAndView mav = new ModelAndView("asignacion-create");

         Asignacion asignacion = new Asignacion();

         // Obtener listas de buses y rutas

         List<Bus> buses = busService.listarbuses();  // Asume que tienes un servicio para obtener buses

         List<Ruta> rutas = rutaService.listarrutas(); // Asume que tienes un servicio para obtener rutas

         mav.addObject("asignacion", asignacion);

         mav.addObject("buses", buses);

         mav.addObject("rutas", rutas);

         return mav;

     }

     @PostMapping(value = "/create")

     public Object crearConductor(@Valid Asignacion asignacion, BindingResult result) {

         if (result.hasErrors()) {

             // Obtener listas de buses y rutas nuevamente en caso de error

             List<Bus> buses = busService.listarbuses();

             List<Ruta> rutas = rutaService.listarrutas();

             ModelAndView mav = new ModelAndView("asignacion-create");

             mav.addObject("asignacion", asignacion);

             mav.addObject("buses", buses);

             mav.addObject("rutas", rutas);

             return mav;

         }

         asignacionService.crearAsignacion(asignacion);

         return new RedirectView("/asignacion/view");

     }

    @PostMapping("/delete/{id}")
    public RedirectView eliminarAsignacion(@PathVariable("id") Long id) {
        Asignacion asignacion = asignacionService.getAsignacionById(id);
        Long conductorId = asignacion.getConductor().getId(); // Obtener el ID del conductor
        asignacionService.eliminarAsignacion(id);
        return new RedirectView("/asignacion/view/" + conductorId); // Redirigir a la vista correcta
    }
}

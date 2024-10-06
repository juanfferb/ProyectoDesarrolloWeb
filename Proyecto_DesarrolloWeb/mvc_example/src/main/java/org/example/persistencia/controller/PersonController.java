package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Person;
import org.example.persistencia.service.PersonService;
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
@RequestMapping("/person")
public class PersonController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ModelAndView listarPersonas() {
        List<Person> persons = personService.listarPersonas();
        ModelAndView modelAndView = new ModelAndView("person-list");
        modelAndView.addObject("persons", persons);
        return modelAndView;
    }

    @GetMapping("/view/{idPersona}")
    public ModelAndView verPersona(@PathVariable("idPersona") Long id) {
        Person person = personService.recuperarPersona(id);
        ModelAndView modelAndView = new ModelAndView("person-view");
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditarPersona(@PathVariable Long id) {
        Person p = personService.recuperarPersona(id);
        ModelAndView modelAndView = new ModelAndView("person-edit");
        modelAndView.addObject("person", p);
        return modelAndView;

    }

    @PostMapping(value = "/save")
    public Object guardarPersona(@Valid Person persona, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("person-edit");
        }
        personService.guardarPersona(persona);
        return new RedirectView("/person/list");
    }

    @GetMapping("/search")
    public ModelAndView listPersons(@RequestParam(required = false) String searchText) {
        List<Person> persons;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            persons = personService.listarPersonas();
        } else {
            log.info("Buscando personas cuyo apellido comienza con {}", searchText);
            persons = personService.buscarPorApellido(searchText);
        }
        ModelAndView modelAndView = new ModelAndView("person-search");
        modelAndView.addObject("persons", persons);
        return modelAndView;

    }
}

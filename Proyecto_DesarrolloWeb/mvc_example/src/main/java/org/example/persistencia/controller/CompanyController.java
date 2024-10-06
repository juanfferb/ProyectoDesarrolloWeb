package org.example.persistencia.controller;

import java.util.List;

import org.example.persistencia.model.Company;
import org.example.persistencia.model.Person;
import org.example.persistencia.service.CompanyService;
import org.example.persistencia.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ModelAndView listaCompanias() {
        List<Company> companies = companyService.listaCompanias();
        ModelAndView companyList = new ModelAndView("company-list");
        companyList.addObject("companies", companies);
        return companyList;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView formularioEditar(@PathVariable Long id) {
        Company company = companyService.recuperarCompania(id);
        ModelAndView companyEdit = new ModelAndView("company-edit");
        companyEdit.addObject("company", company);
        return companyEdit;
    }

    @PostMapping(value = "/save")
    public Object guardarCompania(@Valid Company company, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("company-edit");
        }
        companyService.guardarCompania(company);
        return new RedirectView("/company/list");
    }

    @GetMapping("/employee-add-form/{id}")
    public ModelAndView formularioAgregarEmpleado(@PathVariable Long id) {
        Company company = companyService.buscarCompania(id);
        List<Person> persons = personService.listarPersonas();
        ModelAndView employeeAdd = new ModelAndView("employee-manage");
        employeeAdd.addObject("company", company);
        employeeAdd.addObject("availablePersons", persons);
        return employeeAdd;
    }

    @GetMapping("/{idCompania}/add/{idEmpleado}")
    public RedirectView agregarEmpleado(
            @PathVariable Long idCompania,
            @PathVariable Long idEmpleado,
            RedirectAttributes redirectAttributes) {

        companyService.asociarEmpleado(idCompania, idEmpleado);

        // https://www.baeldung.com/spring-redirect-and-forward
        redirectAttributes.addAttribute("id", idCompania);
        return new RedirectView("/company/employee-add-form/{id}");
    }

}

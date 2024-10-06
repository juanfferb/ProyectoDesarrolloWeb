package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Company;
import org.example.persistencia.model.Person;
import org.example.persistencia.repository.CompanyRepository;
import org.example.persistencia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    public Company buscarCompania(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void asociarEmpleado(Long idCompania, Long idEmpleado) {
        // Ejercicio: cómo se puede optimizar este método?
        Company company = companyRepository.findById(idCompania).orElseThrow();
        Person employee = personRepository.findById(idEmpleado).orElseThrow();

        company.addEmployee(employee);
        employee.addEmployer(company);

        companyRepository.save(company);
        personRepository.save(employee);

    }

    public List<Company> listaCompanias() {
        return companyRepository.findAll();
    }

    public Company recuperarCompania(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public Company guardarCompania(Company company) {
        return companyRepository.save(company);
    }
}

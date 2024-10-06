package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Person;
import org.example.persistencia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> listarPersonas() {
        return personRepository.findAll();
    }

    public Person recuperarPersona(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public void guardarPersona(Person person) {
        personRepository.save(person);
    }

    public List<Person> buscarPorApellido(String textoBusqueda) {
        return personRepository.findAllByLastName(textoBusqueda);
        // return personRepository.findAllByLastNameStartingWith(textoBusqueda);
        // return personRepository.findAllByLastNameStartingWithIgnoreCase(textoBusqueda);
        // return personRepository.findPersonsByLastNameStartingWithCaseInsensitive(textoBusqueda);
        // return personRepository.findPersonsByLastNameStartingWith(textoBusqueda);
    }

}

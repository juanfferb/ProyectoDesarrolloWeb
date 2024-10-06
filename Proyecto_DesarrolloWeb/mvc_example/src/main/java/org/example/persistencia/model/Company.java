package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    // Al usar mappedBy estamos indicando que el otro lado
    // de la asociación es el "dueño" de la asociación
    // https://stackoverflow.com/a/21068644
    private List<Division> divisions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "company_employee",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Person> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public boolean addEmployee(Person e) {
        return employees.add(e);
    }

    public boolean addDivision(Division div) {
        return divisions.add(div);
    }

    

}
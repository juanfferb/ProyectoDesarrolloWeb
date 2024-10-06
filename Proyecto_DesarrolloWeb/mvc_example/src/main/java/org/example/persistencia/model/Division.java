package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    // cascade = CascadeType.PERSIST sirve para evitar el siguiente error:
    // org.hibernate.TransientPropertyValueException: object references an unsaved
    // transient instance - save the transient instance before flushing
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "division")
    private List<Role> roles = new ArrayList<>();

    public Division() {
    }

    public Division(String name) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean addRole(Role e) {
        return roles.add(e);
    }
}

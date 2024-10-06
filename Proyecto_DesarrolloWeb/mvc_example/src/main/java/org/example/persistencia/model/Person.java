package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "no puede estar en blanco") // Para agregar internacionalización:
                                                    // https://www.baeldung.com/spring-custom-validation-message-source
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    @Email(message = "debe ser una dirección de correo válida")
    private String email;

    @ManyToMany(mappedBy = "employees")
    private List<Company> employers = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Role> roles = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addEmployer(Company employer) {
        return employers.add(employer);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean addRole(Role e) {
        return roles.add(e);
    }

    
}

package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre de la ruta no puede estar en blanco")
    private String nombre;

    @OneToMany(mappedBy = "ruta")
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Ruta() {
    }

    public Ruta(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public boolean addAsignacion(Asignacion asignacion) {
        return asignaciones.add(asignacion);
    }
}
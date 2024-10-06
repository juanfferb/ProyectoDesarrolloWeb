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
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "conductor")
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @Column(name = "cedula", unique = true, nullable = false)
    @NotBlank(message = "La cédula no puede estar en blanco")
    private String cedula;

    @Column(name = "telefono", nullable = false)
    @NotBlank(message = "El teléfono no puede estar en blanco")
    private String telefono;

    @Column(name = "direccion", nullable = false)
    @NotBlank(message = "La dirección no puede estar en blanco")
    private String direccion;

    @ManyToMany(mappedBy = "conductores")
    private List<Bus> busesAsignados = new ArrayList<>();

    @OneToMany(mappedBy = "conductor")
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Conductor() {
    }

    public Conductor(String nombre, String cedula, String telefono, String direccion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Bus> getBusesAsignados() {
        return busesAsignados;
    }

    public boolean addBusAsignado(Bus bus) {
        return busesAsignados.add(bus);
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public boolean addAsignacion(Asignacion asignacion) {
        return asignaciones.add(asignacion);
    }
}

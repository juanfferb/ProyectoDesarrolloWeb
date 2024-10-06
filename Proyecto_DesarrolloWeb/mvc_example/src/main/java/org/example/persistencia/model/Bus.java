package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", unique = true, nullable = false)
    @NotBlank(message = "La placa no puede estar en blanco")
    private String placa;

    @Column(name = "modelo", nullable = false)
    @NotBlank(message = "El modelo no puede estar en blanco")
    private String modelo;

    @ManyToMany
    @JoinTable(
        name = "conductor_bus",
        joinColumns = @JoinColumn(name = "bus_id"),
        inverseJoinColumns = @JoinColumn(name = "conductor_id")
    )
    private List<Conductor> conductores = new ArrayList<>(); 

    @OneToMany(mappedBy = "bus")
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Bus() {
    }

    public Bus(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Conductor> getConductoresAsignados() {
        return conductores;
    }

    public boolean addConductorAsignado(Conductor conductor) {
        return conductores.add(conductor);
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public boolean addAsignacion(Asignacion asignacion) {
        return asignaciones.add(asignacion);
    }
}

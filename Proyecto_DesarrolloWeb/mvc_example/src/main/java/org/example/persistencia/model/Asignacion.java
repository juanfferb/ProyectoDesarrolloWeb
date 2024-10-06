package org.example.persistencia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "asignacion")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "El conductor no puede ser nulo")
    private Conductor conductor;

    @ManyToOne
    @NotNull(message = "El bus no puede ser nulo")
    private Bus bus;

    @ManyToOne
    @NotNull(message = "La ruta no puede ser nula")
    private Ruta ruta;

    @Column(name = "dias_asignacion", nullable = false)
    @NotNull(message = "Los días de asignación no pueden ser nulos")
    private String diasAsignacion; 

    public Asignacion() {
    }

    public Asignacion(Conductor conductor, Bus bus, Ruta ruta, String diasAsignacion) {
        this.conductor = conductor;
        this.bus = bus;
        this.ruta = ruta;
        this.diasAsignacion = diasAsignacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getDiasAsignacion() {
        return diasAsignacion;
    }

    public void setDiasAsignacion(String diasAsignacion) {
        this.diasAsignacion = diasAsignacion;
    }
}

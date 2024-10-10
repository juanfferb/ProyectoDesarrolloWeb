package org.example.persistencia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "codigo", nullable = false)
    @NotBlank(message = "El codigo de la ruta no puede estar en blanco")
    private String codigo;

    @Column(name = "estaciones", nullable = false)
    @NotBlank(message = "Las estaciones de la ruta no pueden estar en blanco")
    private String estaciones;

    @Column(name = "horario", nullable = false)
    @NotBlank(message = "El horario de la ruta no puede estar en blanco")
    private String horario;

    @OneToMany(mappedBy = "ruta")
    @JsonIgnore
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Ruta() {
    }

    public Ruta(String codigo, String estaciones, String horario) {
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String Horario) {
        this.horario = horario;
    }

    public String getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(String Estaciones) {
        this.estaciones = estaciones;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public boolean addAsignacion(Asignacion asignacion) {
        return asignaciones.add(asignacion);
    }
}
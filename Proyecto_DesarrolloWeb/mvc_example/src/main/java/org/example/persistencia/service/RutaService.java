package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Ruta;
import org.example.persistencia.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> listarrutas() {
        return rutaRepository.findAll();
    }

    public Ruta recuperarRuta(Long id) {
        return rutaRepository.findById(id).orElseThrow();
    }
}

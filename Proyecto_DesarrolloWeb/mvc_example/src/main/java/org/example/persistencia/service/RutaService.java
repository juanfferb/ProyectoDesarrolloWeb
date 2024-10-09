package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Ruta;
import org.example.persistencia.conversion.RutaDTOConverter;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.repository.AsignacionRepository;
import org.example.persistencia.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private AsignacionRepository asignacionRepository;
    @Autowired
    private RutaDTOConverter rutaDTOConverter;

    public List<Ruta> listarrutas() {
        return rutaRepository.findAll();
    }

    public RutaDTO recuperarRuta(Long id) {
        return rutaDTOConverter.entityToDTO(rutaRepository.findById(id).orElseThrow());
    }
}

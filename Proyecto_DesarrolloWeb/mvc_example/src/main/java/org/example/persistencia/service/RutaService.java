package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.conversion.RutaDTOConverter;
import org.example.persistencia.dto.RutaDTO;
import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.repository.RutaRepository;
import org.example.persistencia.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaService {
    @Autowired
    private RutaRepository RutaRepository;
    @Autowired
    private AsignacionRepository asignacionRepository;
    @Autowired
    private RutaDTOConverter RutaDTOConverter;


    public List<RutaDTO> listarRutas() {
        List<Ruta> Rutaes = RutaRepository.findAll();
        return RutaDTOConverter.entityToDTO(Rutaes); 
    }

    public RutaDTO recuperarRuta(Long id) {
        return RutaDTOConverter.entityToDTO(RutaRepository.findById(id).orElseThrow());
    }

    public void guardarRuta(Ruta Ruta) {
        RutaRepository.save(Ruta);
    }

    public List<RutaDTO> buscarPorCodigo(String textoBusqueda) {
        return RutaRepository.findAllByCodigoStartingWith(textoBusqueda);
    }

    // Nuevo método para crear un Ruta
    public RutaDTO crearRuta(RutaDTO RutaDTO) {
        Ruta Ruta = RutaDTOConverter.DTOToEntity(RutaDTO);
        return RutaDTOConverter.entityToDTO(RutaRepository.save(Ruta));
    }

    public void eliminarRuta(Long id) {
        // Primero eliminamos las asignaciones asociadas al Ruta
        List<Asignacion> asignaciones = asignacionRepository.findAsignacionesByRutaId(id);
        for (Asignacion asignacion : asignaciones) {
            asignacionRepository.delete(asignacion);
        }

        // Luego eliminamos el Ruta
        RutaDTO RutaDTO = recuperarRuta(id);
        //RutaRepository.delete(RutaDTO);
    }
}

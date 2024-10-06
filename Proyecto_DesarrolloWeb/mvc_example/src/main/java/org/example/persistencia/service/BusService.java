package org.example.persistencia.service;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.example.persistencia.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public List<Bus> listarbuses() {
        return busRepository.findAll();
    }

    public Bus recuperarBus(Long id) {
        return busRepository.findById(id).orElseThrow();
    }
}

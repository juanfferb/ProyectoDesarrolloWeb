package org.example.persistencia.repository;

import org.example.persistencia.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}

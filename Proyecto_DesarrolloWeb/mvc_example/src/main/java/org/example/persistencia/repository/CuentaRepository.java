package org.example.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.persistencia.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}

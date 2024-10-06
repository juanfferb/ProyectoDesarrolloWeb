package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

}

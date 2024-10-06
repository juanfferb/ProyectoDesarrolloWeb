package org.example.persistencia.repository;

import org.example.persistencia.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

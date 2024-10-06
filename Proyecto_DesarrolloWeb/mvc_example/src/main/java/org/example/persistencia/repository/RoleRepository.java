package org.example.persistencia.repository;

import org.example.persistencia.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

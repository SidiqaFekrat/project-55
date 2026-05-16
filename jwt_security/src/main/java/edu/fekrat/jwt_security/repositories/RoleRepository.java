package edu.fekrat.jwt_security.repositories;


import edu.fekrat.jwt_security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

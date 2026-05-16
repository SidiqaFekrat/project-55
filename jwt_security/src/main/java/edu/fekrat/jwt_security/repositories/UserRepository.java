package edu.fekrat.jwt_security.repositories;

import edu.fekrat.jwt_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.fekrat.jwt_security.entities.Role;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
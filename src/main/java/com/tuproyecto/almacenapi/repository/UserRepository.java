package com.tuproyecto.almacenapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuproyecto.almacenapi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

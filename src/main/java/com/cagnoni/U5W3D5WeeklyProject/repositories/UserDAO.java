package com.cagnoni.U5W3D5WeeklyProject.repositories;

import com.cagnoni.U5W3D5WeeklyProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}

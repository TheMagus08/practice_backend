package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


}

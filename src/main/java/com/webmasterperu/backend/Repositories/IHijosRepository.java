package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.Hijos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHijosRepository extends JpaRepository<Hijos, Integer> {
    List<Hijos> findByUserId(Integer userId);
}

package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findByDepartamentoId(String departamentoId);
}

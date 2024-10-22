package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {
}

package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.TipoSector;
import com.webmasterperu.backend.utils.enums.ESector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoSectorRepository extends JpaRepository<TipoSector, Long> {
    Optional<TipoSector> findByDescripcion(ESector descripcion);

}
package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.TipoEstado;
import com.webmasterperu.backend.utils.enums.EEstadoOferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoEstadoRepository extends JpaRepository<TipoEstado, Integer> {
    Optional<TipoEstado> findByDescripcion(EEstadoOferta descripcion);
}

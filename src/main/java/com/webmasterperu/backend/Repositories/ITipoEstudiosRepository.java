package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.TipoEstudios;
import com.webmasterperu.backend.utils.enums.ETiposEstudios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoEstudiosRepository extends JpaRepository<TipoEstudios,Long> {
    Optional<TipoEstudios> findByDescripcion(ETiposEstudios descripcion);
}

package com.webmasterperu.backend.Repositories;


import com.webmasterperu.backend.Entities.TipoTiempo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoTiempoRepository extends JpaRepository<TipoTiempo, Long> {
    Optional<TipoTiempo> findByDescripcion(TipoTiempo descripcion);

}

package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.TipoSexo;
import com.webmasterperu.backend.utils.enums.ESexo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ITipoSexoRepository  extends JpaRepository<TipoSexo, Long> {
    Optional<TipoSexo> findByDescripcion(ESexo descripcion);

}

package com.webmasterperu.backend.Repositories;


import com.webmasterperu.backend.utils.list.TipoDocumento;
import com.webmasterperu.backend.utils.list.TipoRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
    Optional<TipoDocumento> findByDocumento(String documento);
}

package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.TipoTiempo;
import com.webmasterperu.backend.utils.list.TipoRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ITipoRoleRepository extends JpaRepository<TipoRole, Long> {

    Optional<TipoRole> findByRole(String role);


    // Verificar si un role con ese nombre ya existe
    boolean existsByRole(String role);

    //nuevo usuario sin admin


}

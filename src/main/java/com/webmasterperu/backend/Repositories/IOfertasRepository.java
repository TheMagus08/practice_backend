package com.webmasterperu.backend.Repositories;

import com.webmasterperu.backend.Entities.Ofertas;
import com.webmasterperu.backend.Entities.TipoSector;
import com.webmasterperu.backend.Entities.TipoTiempo;
import com.webmasterperu.backend.utils.enums.EEstadoOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOfertasRepository  extends JpaRepository<Ofertas, Integer> {

    //List<Ofertas> findByEstadoDescripcion(EEstadoOferta descripcion);

    List<Ofertas> findByEstadoDescripcionAndTipoSector(EEstadoOferta estado, TipoSector tipoSector);

    List<Ofertas> findByTipoSectorAndEnumTiempo(TipoSector  tipoSector, TipoTiempo tiempo); // Cambiar a findByTipoSectorAndEnumTiempo

    List<Ofertas> findByEnumTiempo(TipoTiempo tiempo); // Cambiar a findByEnumTiempo

    List<Ofertas> findByTipoSector(TipoSector tipoSector); // Cambia el parámetro a TipoSector

    //List<Ofertas> findByEstadoPublicado();

    @Query("SELECT o FROM Ofertas o WHERE o.estado.descripcion = :estado")
    List<Ofertas> findByEstadoDescripcion(@Param("estado") EEstadoOferta estado);

    // Método para encontrar solo ofertas publicadas
    @Query("SELECT o FROM Ofertas o WHERE o.estado.descripcion = 'PUBLICADO'")
    List<Ofertas> findByEstadoPublicado(); // Ahora este método debería funcionar

    List<Ofertas> findByTipoSectorAndEstadoDescripcion(TipoSector tipoSector, EEstadoOferta estado);

    List<Ofertas> findByEnumTiempoAndEstadoDescripcion(TipoTiempo tiempo, EEstadoOferta estado);

    List<Ofertas> findByTipoSectorAndEnumTiempoAndEstadoDescripcion(TipoSector tipoSector, TipoTiempo tiempo, EEstadoOferta estado);
}

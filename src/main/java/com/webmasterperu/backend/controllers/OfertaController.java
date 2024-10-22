package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Entities.Ofertas;
import com.webmasterperu.backend.Entities.TipoSector;
import com.webmasterperu.backend.Entities.TipoTiempo;
import com.webmasterperu.backend.Repositories.IOfertasRepository;
import com.webmasterperu.backend.Repositories.ITipoSectorRepository;
import com.webmasterperu.backend.Repositories.ITipoTiempoRepository;
import com.webmasterperu.backend.Services.OfertaService;
import com.webmasterperu.backend.utils.enums.EEstadoOferta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ofertas")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class OfertaController {


    @Autowired
    private OfertaService ofertasService;

    @Autowired
    private IOfertasRepository ofertasRepository;

    @Autowired
    private ITipoTiempoRepository tipoTiempoRepository;

    @Autowired
    private ITipoSectorRepository tipoSectorRepository;

    // Obtener todas las ofertas
    @GetMapping
    public List<Ofertas> getAllOfertas() {
        return ofertasService.findAll();
    }

    // Obtener oferta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ofertas> getOfertaById(@PathVariable Integer id) {
        Optional<Ofertas> oferta = ofertasService.findById(id);
        return oferta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/publicadas")
    public List<Ofertas> listarOfertasPublicadas() {
        return ofertasService.obtenerOfertasPublicadas();
    }

    // Crear una nueva oferta
    @PostMapping
    public Ofertas createOferta(@RequestBody Ofertas oferta) {
        return ofertasService.save(oferta);
    }

    // Actualizar una oferta existente
    @PutMapping("/{id}")
    public ResponseEntity<Ofertas> updateOferta(@PathVariable Integer id, @RequestBody Ofertas ofertaDetails) {
        try {
            Ofertas updatedOferta = ofertasService.update(id, ofertaDetails);
            return ResponseEntity.ok(updatedOferta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/publicadas/filtrar")
    public List<Ofertas> filtrarOfertas(
            @RequestParam(required = false) Long sectorId,
            @RequestParam(required = false) Long tiempoId) {

        TipoSector tipoSector = null;
        if (sectorId != null) {
            tipoSector = tipoSectorRepository.findById(sectorId).orElse(null); // Obtener el objeto TipoSector
        }


        TipoTiempo tiempo = null;
        if (tiempoId != null) {
            // Obtener la instancia de TipoTiempo desde el repositorio
            tiempo = tipoTiempoRepository.findById(tiempoId).orElse(null);
        }

        if (sectorId != null && tiempoId != null) {
            return ofertasRepository.findByTipoSectorAndEnumTiempoAndEstadoDescripcion(tipoSector, tiempo, EEstadoOferta.PUBLICADO);
        } else if (sectorId != null) {
            return ofertasRepository.findByTipoSectorAndEstadoDescripcion(tipoSector, EEstadoOferta.PUBLICADO);
        } else if (tiempoId != null) {
            return ofertasRepository.findByEnumTiempoAndEstadoDescripcion(tiempo, EEstadoOferta.PUBLICADO);
        } else {
            return ofertasRepository.findByEstadoPublicado();
        }
    }

    @GetMapping("/publicadas/sector")
    public List<Ofertas> listarOfertasPorSector(@RequestParam Long sectorId) {
        // Crear una instancia de TipoSector con el id proporcionado
        TipoSector tipoSector = new TipoSector();
        tipoSector.setId(sectorId);

        return ofertasService.obtenerOfertasPorSector(tipoSector);
    }

    // Eliminar una oferta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable Integer id) {
        ofertasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

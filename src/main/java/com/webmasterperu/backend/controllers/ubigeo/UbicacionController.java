package com.webmasterperu.backend.controllers.ubigeo;

import com.webmasterperu.backend.Entities.Departamento;
import com.webmasterperu.backend.Entities.Distrito;
import com.webmasterperu.backend.Entities.Provincia;
import com.webmasterperu.backend.Repositories.IDepartamentoRepository;
import com.webmasterperu.backend.Repositories.IDistritoRepository;
import com.webmasterperu.backend.Repositories.IProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UbicacionController {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Autowired
    private IDistritoRepository distritoRepository;

    // Endpoint para listar todos los departamentos
    @GetMapping("/departamentos")
    public List<Departamento> getDepartamentos() {
        return departamentoRepository.findAll();
    }

    // Endpoint para listar todas las provincias por departamento
    @GetMapping("/departamentos/{departamentoId}/provincias")
    public List<Provincia> getProvinciasByDepartamento(@PathVariable String departamentoId) {
        return provinciaRepository.findAll().stream()
                .filter(provincia -> provincia.getDepartamento().getId().equals(departamentoId))
                .toList();
    }

    // Endpoint para listar todos los distritos por provincia
    @GetMapping("/provincias/{provinciaId}/distritos")
    public List<Distrito> getDistritosByProvincia(@PathVariable String provinciaId) {
        return distritoRepository.findAll().stream()
                .filter(distrito -> distrito.getProvincia().getId().equals(provinciaId))
                .toList();
    }
}

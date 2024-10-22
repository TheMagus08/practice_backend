package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoEstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoEstadoController {

    @Autowired
    ITipoEstadoRepository tipoEstadoRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoEstado(){
        return ResponseEntity.ok(tipoEstadoRepository.findAll());
    }
}

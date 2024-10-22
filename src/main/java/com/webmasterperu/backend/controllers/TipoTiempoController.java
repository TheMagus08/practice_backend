package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoTiempoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tiempo")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoTiempoController {

    @Autowired
    ITipoTiempoRepository tipoTiempoRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoTiempo(){
        return ResponseEntity.ok(tipoTiempoRepository.findAll());
    }
}

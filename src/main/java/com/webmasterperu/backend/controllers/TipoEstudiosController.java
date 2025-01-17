package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoEstudiosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudios")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoEstudiosController {

    @Autowired
    ITipoEstudiosRepository tipoEstudiosRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoEstudios(){
        return ResponseEntity.ok(tipoEstudiosRepository.findAll());
    }
}

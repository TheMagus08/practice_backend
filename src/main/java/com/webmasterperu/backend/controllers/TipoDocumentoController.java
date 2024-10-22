package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/documento")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoDocumentoController {

    @Autowired
    ITipoDocumentoRepository tipoDocumentoRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoDocumento(){
        return ResponseEntity.ok(tipoDocumentoRepository.findAll());
    }
}

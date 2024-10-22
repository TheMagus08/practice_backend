package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoSexoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sexo")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoSexoController {

    @Autowired
    ITipoSexoRepository tipoSexoRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoSexo(){
        return ResponseEntity.ok(tipoSexoRepository.findAll());
    }
}

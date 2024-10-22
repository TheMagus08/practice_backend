package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoSectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sector")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoSectorController {

    @Autowired
    ITipoSectorRepository tipoSectorRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoSector(){
        return ResponseEntity.ok(tipoSectorRepository.findAll());
    }

}

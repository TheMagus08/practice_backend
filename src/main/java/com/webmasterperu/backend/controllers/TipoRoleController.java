package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Repositories.ITipoRoleRepository;
import com.webmasterperu.backend.Services.AuthService;
import com.webmasterperu.backend.utils.list.TipoRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoRoleController {

    @Autowired
    ITipoRoleRepository tipoRoleRepository;

    private final AuthService authService;


    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoRole(){
        return ResponseEntity.ok(tipoRoleRepository.findAll());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<TipoRole>> getRoles() {
        List<TipoRole> roles = authService.getAllRolesExceptAdmin();
        return ResponseEntity.ok(roles);
    }
}

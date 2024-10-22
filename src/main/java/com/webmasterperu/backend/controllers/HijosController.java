package com.webmasterperu.backend.controllers;

import com.webmasterperu.backend.Entities.Hijos;
import com.webmasterperu.backend.Entities.TipoSexo;
import com.webmasterperu.backend.Repositories.IHijosRepository;
import com.webmasterperu.backend.Repositories.ITipoSexoRepository;
import com.webmasterperu.backend.Services.HijosService;
import com.webmasterperu.backend.jwt.JwtService;
import com.webmasterperu.backend.utils.dto.HijosDTO;
import com.webmasterperu.backend.utils.enums.ESexo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/hijos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class HijosController {

    @Autowired
    private HijosService hijosService;

    private final IHijosRepository hijosRepository;
    private final ITipoSexoRepository tipoSexoRepository;
    @Autowired
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<HijosDTO> addHijo(@RequestBody HijosDTO hijo) {
        HijosDTO createdHijo = hijosService.addHijo(hijo);
        return new ResponseEntity<>(createdHijo, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HijosDTO>> getHijosByUserId(@PathVariable Integer userId /*, @RequestHeader("Authorization") String token*/) {

        /*Claims claims = jwtService.getAllClaims(token.substring(7));
        Integer userIdFromToken = claims.get("userId", Integer.class);*/


        List<Hijos> hijos = hijosService.getHijosByUserId(userId);

        List<HijosDTO> hijosDTO = hijos.stream()
                .map(hijo -> new HijosDTO(
                        hijo.getId(),
                        hijo.getNombres(),
                        hijo.getTiposexo().getDescripcion(),
                        hijo.getFechaNacimiento(),
                        hijo.getDni()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(hijosDTO, HttpStatus.OK);

    }



    @GetMapping("/{id}")
    public ResponseEntity<Hijos> getHijoById(@PathVariable Integer id, @RequestHeader("Authorization") String token) {

        Claims claims = jwtService.getAllClaims(token.substring(7));
        Integer userIdFromToken = claims.get("userId", Integer.class);

        Optional<Hijos> hijo = hijosService.getHijoById(id);
        return hijo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHijo(@PathVariable Integer id) {
        hijosService.deleteHijo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HijosDTO> updateHijo(@PathVariable Integer id, @RequestBody HijosDTO hijoDetails) {
        Hijos existingHijo = hijosService.getHijoById(id)
                .orElseThrow(() -> new RuntimeException("Hijo no encontrado con id " + id));

        // Asignar los detalles actualizados
        existingHijo.setNombres(hijoDetails.getNombres());
        existingHijo.setFechaNacimiento(hijoDetails.getFechaNacimiento());
        existingHijo.setDni(hijoDetails.getDni());

        if (hijoDetails.getSexo() != null) {
            // Convertir la cadena a ESexo
            ESexo sexoEnum;
            try {
                sexoEnum = ESexo.valueOf(hijoDetails.getSexo().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("TipoSexo no válido: " + hijoDetails.getSexo());
            }

            // Buscar el TipoSexo basado en el enum
            TipoSexo tipoSexo = tipoSexoRepository.findByDescripcion(sexoEnum)
                    .orElseThrow(() -> new RuntimeException("TipoSexo no encontrado para la descripción " + sexoEnum));
            existingHijo.setTiposexo(tipoSexo);
        }

        // Guardar los cambios
        Hijos updatedHijo = hijosRepository.save(existingHijo);

        // Crear y devolver el DTO de respuesta
        HijosDTO responseDTO = new HijosDTO();
        responseDTO.setId(updatedHijo.getId());
        responseDTO.setNombres(updatedHijo.getNombres());
        responseDTO.setSexo(updatedHijo.getTiposexo().getDescripcion());
        responseDTO.setFechaNacimiento(updatedHijo.getFechaNacimiento());
        responseDTO.setDni(updatedHijo.getDni());

        return ResponseEntity.ok(responseDTO);
    }
}

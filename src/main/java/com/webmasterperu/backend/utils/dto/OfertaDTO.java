package com.webmasterperu.backend.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfertaDTO {

    int id;
    String fechaInicio;
    String fechaFin;
    String sector;
    String industria;
    String titulo;
    String empresa;
    String modalidad;
    String tiempo;
    String salario;
    String estado;
}

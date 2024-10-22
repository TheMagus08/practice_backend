package com.webmasterperu.backend.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HijosDTO {
    private Integer id;
    private String nombres;
    private String sexo;
    private String fechaNacimiento;
    private String dni;



}
package com.webmasterperu.backend.utils.dto;

import com.webmasterperu.backend.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    int id;
    String username;

    String tipoDocumento;
    String numeroDocumento;

    String primerNombre;
    String segundoNombre;
    String role;
    String codigo;
    String apellidoM;
    String apellidoP;
    String sexo;
    String fechaNac;
    String ciudadNac;
    String estadoCivil;

    String telefono1;
    String telefono2;
    String direccion;
    String urbanizacion;

    String departamento;
    String provincia;
    String distrito;

    String linkedin;
    String facebook;
    String instagram;



}

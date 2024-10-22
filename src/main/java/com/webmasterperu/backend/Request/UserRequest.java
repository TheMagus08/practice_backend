package com.webmasterperu.backend.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    int id;
    String username;


    String primerNombre;
    String segundoNombre;
    String tipoDocumento;
    String numeroDocumento;
    String codigo;
    String apellidoM;
    String apellidoP;
    String sexo;
    String fechaNac;
    String ciudadNac;
    String estadoCivil;
    String role;

    String telefono1;
    String telefono2;


}

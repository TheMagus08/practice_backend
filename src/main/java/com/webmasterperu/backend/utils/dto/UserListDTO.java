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
public class UserListDTO {

    int id;
    String primerNombre;
    String segundoNombre;
    String apellidoP;
    String apellidoM;
    String codigo;
    String username;
    String numeroDocumento;
    String role;

}

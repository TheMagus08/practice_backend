package com.webmasterperu.backend.Request;

import com.webmasterperu.backend.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String tipoDocumento;
    String tipoSexo;
   /* String numeroDocumento;
    String codigo;
    String primerNombre;
    String segundoNombre;
    String apellidoP;String fechaNacimiento;
    String apellidoM;*/
    String role;

}

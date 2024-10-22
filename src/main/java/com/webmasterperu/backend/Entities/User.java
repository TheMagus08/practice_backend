package com.webmasterperu.backend.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.webmasterperu.backend.utils.enums.Role;
import com.webmasterperu.backend.utils.list.TipoDocumento;
import com.webmasterperu.backend.utils.list.TipoRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Basic
    @Column(nullable = false)
    String username;
    String password;

    //DATOS PARA CREAR

    //perfil

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private TipoDocumento tipoDocumento;
    String numeroDocumento;  //DATOS PARA CREAR

    // rol  //DATOS PARA CREAR
    // email  //DATOS PARA CREAR

    String primerNombre;  //DATOS PARA CREAR
    String segundoNombre;  //DATOS PARA CREAR
    String codigo;  //DATOS PARA CREAR
    String apellidoM;  //DATOS PARA CREAR
    String apellidoP;  //DATOS PARA CREAR

    @ManyToOne
    @JoinColumn(name = "id_tipo_sexo")
    private TipoSexo tiposexo;  //DATOS PARA CREAR

    String fechaNac;  //DATOS PARA CREAR
    String ciudadNac;
    String estadoCivil;

    //contacto
    String telefono1;
    String telefono2;
    String email;
    String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id") // Asegúrate de que este sea el nombre correcto de la columna en tu base de datos
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id") // Asegúrate de que este sea el nombre correcto de la columna en tu base de datos
    private Provincia provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id") // Asegúrate de que este sea el nombre correcto de la columna en tu base de datos
    private Distrito distrito;
    String urbanizacion;

    String linkedin;
    String facebook;
    String instagram;

    //academico
    String universidad;
    String anhoUniversidad;
    String titulo;
    String anhoTitulo;

    //laboral
    String giro;
    String centroLaboral;
    String website;
    String cargo;
    String fechaIngreso;
    

    //info adicional
    String deportes;
    String hobby;
    String interes;

    String ofimatica;

    String ingles;

    String pensamiento;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private TipoRole role;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Hijos> hijos;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.getRole())));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
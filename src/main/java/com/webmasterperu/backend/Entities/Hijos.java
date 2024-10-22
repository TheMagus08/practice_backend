package com.webmasterperu.backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hijos")
public class Hijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    String nombres;

    @ManyToOne
    @JoinColumn(name = "id_tipo_sexo")
    private TipoSexo tiposexo;

    String fechaNacimiento;
    String dni;


}

package com.webmasterperu.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ofertas")
public class Ofertas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String fechaInicio;
    String fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_tipo_sector")
    private TipoSector tipoSector;


    String Industria;
    String titulo;
    String empresa;
    String modalidad;

    @ManyToOne
    @JoinColumn(name = "id_tiempo")
    private TipoTiempo enumTiempo;

    String salario;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private TipoEstado estado;
}
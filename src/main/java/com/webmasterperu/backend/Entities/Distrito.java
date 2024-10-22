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
@Table(name="distritos")
public class Distrito {

    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}
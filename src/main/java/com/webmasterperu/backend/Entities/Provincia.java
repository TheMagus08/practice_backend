package com.webmasterperu.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="provincias")
public class Provincia {

    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}
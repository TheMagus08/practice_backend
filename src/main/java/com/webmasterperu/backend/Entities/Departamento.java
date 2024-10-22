package com.webmasterperu.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="departamentos")
public class Departamento {

    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "departamento")
    @JsonIgnore // Evitar la serialización infinita
    private List<Provincia> provincias;
}
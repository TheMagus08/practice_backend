package com.webmasterperu.backend.Entities;

import com.webmasterperu.backend.utils.enums.ETiposEstudios;
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
@Table(name="tipo_estudios")
public class TipoEstudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private ETiposEstudios descripcion;

    public String getDescripcion() {
        return descripcion != null ? descripcion.name() : null;
    }
}

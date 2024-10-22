package com.webmasterperu.backend.Entities;

import com.webmasterperu.backend.utils.enums.ESexo;
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
@Table(name="otros_estudios")
public class OtrosEstudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @ManyToOne
    @JoinColumn(name = "id_tipo_estudio")
    private TipoEstudios tipoEstudios;

    String  descripcion;


}

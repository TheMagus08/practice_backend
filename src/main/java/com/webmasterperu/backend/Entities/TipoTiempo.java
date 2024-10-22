package com.webmasterperu.backend.Entities;
import com.webmasterperu.backend.utils.enums.ETiempo;
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
@Table(name="tipo_tiempo")
public class TipoTiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   String descripcion;

}

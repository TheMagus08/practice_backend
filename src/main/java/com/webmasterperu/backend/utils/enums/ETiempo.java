package com.webmasterperu.backend.utils.enums;

public enum ETiempo {
    COMPLETO("Tiempo completo"),
    PARCIAL("Tiempo parcial"),
    FDS("Fin de semana"),
    PRACTICAS("Practicas/pasantias"),
    NOCTURNO("Nocturno"),
    TEMPORAL("Temporal"),
    CONTRATO("Contrato");

    private String descripcion;

    ETiempo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

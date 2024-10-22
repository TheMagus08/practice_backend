package com.webmasterperu.backend.utils.enums;

public enum EnumOfimatica {
    POCAS_VECES("Pocas veces"),
    OCASIONALMENTE("Ocasionalmente"),
    FRECUENTEMENTE("Frecuentemente"),
    SIEMPRE("Siempre");

    private String descripcion;

    EnumOfimatica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}

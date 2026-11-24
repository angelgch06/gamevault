package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para registrar un nuevo prestamo")
public class PrestamoRequestDTO {
    @Schema(description = "id del socio que pide el préstamo",example = "2")
    private Integer idSocio;
    @Schema(description = "id de la copia a prestar",example = "1")
    private Integer idCopia;

    public PrestamoRequestDTO(Integer idSocio, Integer idCopia) {
        this.idSocio = idSocio;
        this.idCopia = idCopia;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }

    public Integer getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(Integer idCopia) {
        this.idCopia = idCopia;
    }
}

package com.gamevault.gamevault.dto;

public class FiltroSociosDTO {
    private String texto;
    private String email;
    private Boolean activo;

    public FiltroSociosDTO(String texto, String email, Boolean activo) {
        this.texto = texto;
        this.email = email;
        this.activo = activo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

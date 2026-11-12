package com.gamevault.gamevault.dto;

import java.time.LocalDate;

public class FiltroPrestamosDTO {
    private String socio;
    private String titulo;
    private String estado; //aqui es donde va lo de en plazo, vencido y devuelta
    private LocalDate fechaDesde;

    public FiltroPrestamosDTO(String socio, String titulo, LocalDate fechaDesde, String estado) {
        this.socio = socio;
        this.titulo = titulo;
        this.fechaDesde = fechaDesde;
        this.estado = estado;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
}

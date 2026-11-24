package com.gamevault.gamevault.dto;

public class CopiaDetalleDTO {
    private int id;
    private String codigoInventario;
    private String titulo;
    private String plataforma;
    private String estado;
    private boolean activa;

    public CopiaDetalleDTO(int id, String codigoInventario, String titulo, String plataforma, String estado, boolean activa) {
        this.id = id;
        this.codigoInventario = codigoInventario;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.estado = estado;
        this.activa = activa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}

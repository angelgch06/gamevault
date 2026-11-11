package com.gamevault.gamevault.dto;

public class CopiaDisponibleDTO {
    private int id;
    private String codigoInventario;
    private String titulo;
    private String plataforma;
    private int pegi;
    private int anioLanzamiento;

    public CopiaDisponibleDTO(int id, String codigoInventario, String titulo, int pegi, String plataforma, int anioLanzamiento) {
        this.id = id;
        this.codigoInventario = codigoInventario;
        this.titulo = titulo;
        this.pegi = pegi;
        this.plataforma = plataforma;
        this.anioLanzamiento = anioLanzamiento;
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

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }
    public String getDescripcion(){
        return titulo+"("+plataforma+") "+codigoInventario;
    }
}

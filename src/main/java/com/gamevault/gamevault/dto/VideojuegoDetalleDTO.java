package com.gamevault.gamevault.dto;

//esta clase es para mandar datos a la vista, como las copias locales, copias disponibles que tiene etc...
public class VideojuegoDetalleDTO {
    private int id;
    private String titulo;
    private int anioLanzamiento;
    private int pegi;
    private int idDesarrolladora;
    private String desarrolladora;
    private int copiasTotales;
    private int copiasDisponibles;

    public VideojuegoDetalleDTO(int id, String titulo, int anioLanzamiento, int pegi, int idDesarrolladora, String desarrolladora, int copiasTotales, int copiasDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.pegi = pegi;
        this.idDesarrolladora = idDesarrolladora;
        this.desarrolladora = desarrolladora;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdDesarrolladora() {
        return idDesarrolladora;
    }

    public void setIdDesarrolladora(int idDesarrolladora) {
        this.idDesarrolladora = idDesarrolladora;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public int getCopiasTotales() {
        return copiasTotales;
    }

    public void setCopiasTotales(int copiasTotales) {
        this.copiasTotales = copiasTotales;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }
}

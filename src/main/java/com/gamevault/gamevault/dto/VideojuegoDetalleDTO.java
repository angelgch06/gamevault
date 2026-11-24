package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//esta clase es para mandar datos a la vista, como las copias locales, copias disponibles que tiene etc...
@Schema(description = "clase detalle de videjuego")
public class VideojuegoDetalleDTO {
    @Schema(description = "id del videojuego",example = "2")
    private int id;
    @Schema(description = "titulo del videojuego" ,example = "gta 6")
    private String titulo;
    @Schema(description = "año de lanzamiento del videojuego" ,example = "2026")
    private int anioLanzamiento;
    @Schema(description = "clasificacion de edad del videojuego" ,example = "18")
    private int pegi;
    @Schema(description = "id de la desarrolladora del videojuego" ,example = "4")
    private int idDesarrolladora;
    @Schema(description = "nombre de la desarrolladora",example = "Rockstar")
    private String desarrolladora;
    @Schema(description = "cantidad de copias del videojuego",example = "1")
    private int copiasTotales;
    @Schema(description = "cantidad de copias disponibles para prestar",example = "5")
    private int copiasDisponibles;
    @Schema(description = "indica si la copia del videojuego se puede prestar o no",example = "true")
    private boolean disponible;

    public VideojuegoDetalleDTO(int id, String titulo, int anioLanzamiento, int pegi, int idDesarrolladora, String desarrolladora, int copiasTotales, int copiasDisponibles, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.pegi = pegi;
        this.idDesarrolladora = idDesarrolladora;
        this.desarrolladora = desarrolladora;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasDisponibles;
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

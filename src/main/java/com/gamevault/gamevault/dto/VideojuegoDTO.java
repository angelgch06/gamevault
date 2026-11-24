package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "clase de videjuego")
public class VideojuegoDTO {
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


    public VideojuegoDTO(int id, String titulo, int anioLanzamiento, int pegi, int idDesarrolladora) {
        this.id = id;
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.pegi = pegi;
        this.idDesarrolladora = idDesarrolladora;
    }

    public VideojuegoDTO() {

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

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public int getIdDesarrolladora() {
        return idDesarrolladora;
    }

    public void setIdDesarrolladora(int idDesarrolladora) {
        this.idDesarrolladora = idDesarrolladora;
    }
}

package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "clase request de videjuego")
public class VideojuegoRequestDTO {
    @Schema(description = "titulo del videojuego" ,example = "gta 6")
    private String titulo;
    @Schema(description = "año de lanzamiento del videojuego" ,example = "2026")
    private Integer anioDesde;//es el año de lanzamiento
    @Schema(description = "id de la desarrolladora del videojuego" ,example = "4")
    private Integer idDesarrolladora;
    @Schema(description = "clasificacion de edad del videojuego" ,example = "18")
    private Integer pegiMax;

    public VideojuegoRequestDTO(String titulo, Integer anioDesde, Integer idDesarrolladora, Integer pegiMax) {
        this.titulo = titulo;
        this.anioDesde = anioDesde;
        this.idDesarrolladora = idDesarrolladora;
        this.pegiMax = pegiMax;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(Integer anioDesde) {
        this.anioDesde = anioDesde;
    }

    public Integer getIdDesarrolladora() {
        return idDesarrolladora;
    }

    public void setIdDesarrolladora(Integer idDesarrolladora) {
        this.idDesarrolladora = idDesarrolladora;
    }

    public Integer getPegiMax() {
        return pegiMax;
    }

    public void setPegiMax(Integer pegiMax) {
        this.pegiMax = pegiMax;
    }
}

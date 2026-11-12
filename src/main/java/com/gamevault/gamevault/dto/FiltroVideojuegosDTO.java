package com.gamevault.gamevault.dto;

public class FiltroVideojuegosDTO {
    private String titulo;
    private Integer idDesarrolladora;
    private Integer anioDesde;
    private Integer pegiMax;
    private Boolean soloDisponibles;

    public FiltroVideojuegosDTO(String titulo, Integer idDesarrolladora, Integer anioDesde, Integer pegiMax, Boolean soloDisponibles) {
        this.titulo = titulo;
        this.idDesarrolladora = idDesarrolladora;
        this.anioDesde = anioDesde;
        this.pegiMax = pegiMax;
        this.soloDisponibles = soloDisponibles;
    }

    public FiltroVideojuegosDTO() {
        this.soloDisponibles=false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Integer getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(Integer anioDesde) {
        this.anioDesde = anioDesde;
    }

    public Boolean getSoloDisponibles() {
        return soloDisponibles;
    }

    public void setSoloDisponibles(Boolean soloDisponibles) {
        this.soloDisponibles = soloDisponibles;
    }
}

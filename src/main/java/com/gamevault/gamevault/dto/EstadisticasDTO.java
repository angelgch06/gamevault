package com.gamevault.gamevault.dto;

public class EstadisticasDTO {
    private int videojuegos;
    private int sociosActivos;
    private int prestamosAbiertos;
    private int prestamosVencidos;

    public EstadisticasDTO(int videojuegos, int prestamosAbiertos, int sociosActivos, int prestamosVencidos) {
        this.videojuegos = videojuegos;
        this.prestamosAbiertos = prestamosAbiertos;
        this.sociosActivos = sociosActivos;
        this.prestamosVencidos = prestamosVencidos;
    }

    public int getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(int videojuegos) {
        this.videojuegos = videojuegos;
    }

    public int getSociosActivos() {
        return sociosActivos;
    }

    public void setSociosActivos(int sociosActivos) {
        this.sociosActivos = sociosActivos;
    }

    public int getPrestamosAbiertos() {
        return prestamosAbiertos;
    }

    public void setPrestamosAbiertos(int prestamosAbiertos) {
        this.prestamosAbiertos = prestamosAbiertos;
    }

    public int getPrestamosVencidos() {
        return prestamosVencidos;
    }

    public void setPrestamosVencidos(int prestamosVencidos) {
        this.prestamosVencidos = prestamosVencidos;
    }
}

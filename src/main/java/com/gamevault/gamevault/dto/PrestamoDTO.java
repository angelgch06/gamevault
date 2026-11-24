package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "clase prestamo")
public class PrestamoDTO {
    @Schema(description = "id de prestamo",example = "1")
    private int id;
    @Schema(description = "id del socio que realiza el préstamo",example = "2")
    private int idSocio;
    @Schema(description = "id de la copia que se presta",example = "3")
    private int idCopia;
    @Schema(description = "fecha en la que se realiza el prestamo",example = "2-5-2024")
    private LocalDate fechaPrestamo;
    @Schema(description = "fecha en la que se debe devolver el prestamo",example = "5-5-2024")
    private LocalDate fechaLimite;
    @Schema(description = "fecha en la que fue devuelto el préstamo",example = "4-5-2024")
    private LocalDate fechaDevolucion;
    @Schema(description = "cantidad a pagar por devolución fuera de fecha límite", example = "10.50")
    private double multa;
//    private boolean renovado;

    public PrestamoDTO(int id, int idCopia, int idSocio, LocalDate fechaPrestamo, LocalDate fechaLimite, LocalDate fechaDevolucion, double multa, boolean renovado) {
        this.id = id;
        this.idCopia = idCopia;
        this.idSocio = idSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
//        this.renovado = renovado;
    }
    public PrestamoDTO() {


    }

//    public boolean isRenovado() {
//        return renovado;
//    }

//    public void setRenovado(boolean renovado) {
//        this.renovado = renovado;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}

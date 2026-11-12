package com.gamevault.gamevault.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PrestamoDetalleDTO {
    private int id;
    private int idSocio;
    private int idCopia;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private LocalDate fechaDevolucion;
    private double multa;
    private String Socio;
    private String titulo;
    private String plataforma;
    private String estado;
    private int retraso;

    public PrestamoDetalleDTO(int id, int idSocio, int idCopia, LocalDate fechaPrestamo, LocalDate fechaLimite, LocalDate fechaDevolucion, double multa, String socio, String titulo, String plataforma) {
        this.id = id;
        this.idSocio = idSocio;
        this.idCopia = idCopia;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = fechaDevolucion;
        this.multa = multa;
        Socio = socio;
        this.titulo = titulo;
        this.plataforma = plataforma;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
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

    public String getSocio() {
        return Socio;
    }

    public void setSocio(String socio) {
        Socio = socio;
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

//    public String getEstado() {
//        return estado;
//    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getRetraso() {
        return retraso;
    }

    public void setRetraso(int retraso) {
        this.retraso = retraso;
    }

    public String getEstado(){
        if(fechaDevolucion!=null){
            return "DEVUELTO";
        }
        //en la linea 135 se hace que se mira la fecha limite si es antes de la actual y si ya pasó, devuelve como que estado es vencido y si no quiere decir que ha vencido pero no se le ha devuelto
        return fechaLimite.isBefore(LocalDate.now())?"VENCIDO":"EN_PLAZO";//is before permite saber si esa fecha es antes de otra fecha, en este caso antes que la actual

    }
    public long getDiasRetraso(){
        //entra al primer if si no hay dias de retraso
        if(fechaDevolucion!=null){
            return 0;
        }
        //entra al segundo if si todavia estoy dentro del rango de fecha limita
        if(!fechaLimite.isBefore(LocalDate.now())){
            return 0;
        }
        //calculo el retraso
        return ChronoUnit.DAYS.between(fechaLimite,LocalDate.now());
    }
}

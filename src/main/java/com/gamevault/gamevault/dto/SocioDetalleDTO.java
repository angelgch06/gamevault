package com.gamevault.gamevault.dto;

import java.time.LocalDate;

public class SocioDetalleDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDate fechaAlta; //todo lo que sea date es localDate (en la base de datos)
    private boolean activo; //todo lo que sea tinyint es entero
    private int edad;
    private int prestamosAbiertos;

    public SocioDetalleDTO(int id, String nombre, String email, String apellidos, LocalDate fechaNacimiento, LocalDate fechaAlta, boolean activo, int edad, int prestamosAbiertos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.edad = edad;
        this.prestamosAbiertos = prestamosAbiertos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPrestamosAbiertos() {
        return prestamosAbiertos;
    }

    public void setPrestamosAbiertos(int prestamosAbiertos) {
        this.prestamosAbiertos = prestamosAbiertos;
    }
}

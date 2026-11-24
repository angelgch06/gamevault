package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "clase del socio")
public class SocioDTO {
    @Schema(description = "id del socio",example = "1")
    private int id;
    @Schema(description = "nombre del socio",example = "Pedro")
    private String nombre;
    @Schema(description = "apellidos del socio",example = "González López")
    private String apellidos;
    @Schema(description = "correo del socio",example = "pedro@gmail.com")
    private String email;
    @Schema(description = "fecha de nacimiento del socio",example = "2-2-2003")
    private LocalDate fechaNacimiento;
    @Schema(description = "fecha en la que el socio fue registrado en el sistema",example = "2-4-2025")
    private LocalDate fechaAlta; //todo lo que sea date es localDate (en la base de datos)
    @Schema(description = "Indica si el socio está activo o no ",example = "true")
    private boolean activo; //todo lo que sea tinyint es entero

    public SocioDTO(int id, String nombre, String apellidos, String email, LocalDate fechaNacimiento, LocalDate fechaAlta, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
    }

    public SocioDTO() {

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
}

package com.gamevault.gamevault.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(description = "clase request del socio")
public class SocioRequestDTO {
    @Schema(description = "nombre del socio",example = "Pedro")
    private String nombre;
    @Schema(description = "apellidos del socio",example = "González López")
    private String apellidos;
    @Schema(description = "correo del socio",example = "pedro@gmail.com")
    private String email;
    @Schema(description = "fecha de nacimiento del socio",example = "2-2-2003")
    private LocalDate fechaNacimiento;
    @Schema(description = "Indica si el socio está activo o no ",example = "true")
    private boolean activo;
    @Schema(description = "fecha en la que el socio fue registrado en el sistema",example = "2-4-2025")
    private LocalDate fechaAlta;

    public SocioRequestDTO(String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.activo=true;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

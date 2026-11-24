package com.gamevault.gamevault.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gamevault.gamevault.dto.*;
import com.gamevault.gamevault.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/prestamos")
public class PrestamoRestController {
    private final PrestamoService pService;


    public PrestamoRestController(PrestamoService pService) {
        this.pService = pService;
    }

    //RF 09 Listar prestamos con filtro
    @Operation(summary = "listar préstamos",description = "obtiene una lista de préstamos aplicando filtros opcionales")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "lista de préstamos devuelta correctamente"),@ApiResponse(responseCode = "400",description = "parámetro de estado no válido")})
    @GetMapping
    public ResponseEntity<List<PrestamoDetalleDTO>> listar(@RequestParam(required = false) String socio, @RequestParam(required = false) String titulo, @RequestParam(required = false) String estado, @RequestParam(required = false) LocalDate fechaDesde) {
        FiltroPrestamosDTO filtro = new FiltroPrestamosDTO(socio, titulo, fechaDesde, estado);
        List<PrestamoDetalleDTO> lista = pService.buscar(filtro);
        return ResponseEntity.ok(lista);
    }

    //RF 010 Devolver prestamo
    @Operation(summary = "obtener préstamo por id",description = "devuelve los detalles de un préstamo específico")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "préstamo encontrado"),@ApiResponse(responseCode = "404",description = "el préstamo no existe")})
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDetalleDTO> obtenerPrestamo(@PathVariable int id) {
        PrestamoDetalleDTO p = pService.buscarPorId(id);
        return ResponseEntity.ok(p);
    }

    //RF 011 Registrar prestamo
    @Operation(summary = "registrar préstamos",description = "registra un nuevo préstamo validando las reglas de negocio")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "préstamo registrado correctamente"),@ApiResponse(responseCode = "404",description = "la copia no existe"),@ApiResponse(responseCode = "422",description = "se ha incumplido una regla de negocio")})
    @PostMapping
    public ResponseEntity<PrestamoDetalleDTO> crear(@RequestBody PrestamoRequestDTO prestamo) {
        PrestamoDetalleDTO p = pService.insert(prestamo.getIdSocio(), prestamo.getIdCopia());
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    //RF 012 Devolver
    @Operation(summary = "devolver un préstamo",description = "marca un préstamo como devuelto, calcula la multa si la hay y actualiza la copia si está dañada")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "préstamo devuelto correctamente"),@ApiResponse(responseCode = "404",description = "el préstamo no existe"),@ApiResponse(responseCode = "409",description = "el préstamo ya se había devuelto")})
    @PutMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoDetalleDTO> devolver(@PathVariable int id, @RequestParam(required = false) boolean danada){
        PrestamoDetalleDTO p=pService.devolver(id,danada);
        return ResponseEntity.ok(p);
    }
}

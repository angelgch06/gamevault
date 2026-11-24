package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.dto.EstadisticasDTO;
import com.gamevault.gamevault.service.EstadisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaRestController {

    private final EstadisticaService eService;


    public EstadisticaRestController(EstadisticaService eService) {
        this.eService = eService;
    }

    //RF 014 Devolver contadores de inicio
    @Operation(summary = "devuelve contadores del inicio",description = "indica los contadores de : videojuegos,socios activos,prestamos abiertos y préstamos vencidos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "contadores generales devueltos correctamente")})
    @GetMapping
    public ResponseEntity<EstadisticasDTO> obtenerEstadisticas(){
        EstadisticasDTO e=eService.obtenerEstadisticas();
        return ResponseEntity.ok(e);
    }
}

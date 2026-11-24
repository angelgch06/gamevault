package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.dto.DesarrolladoraDTO;
import com.gamevault.gamevault.service.DesarrolladoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladoras")
public class DesarrolladoraRestController {

    private final DesarrolladoraService dService;


    public DesarrolladoraRestController(DesarrolladoraService dService) {
        this.dService = dService;
    }

    //RF 013 obtener lista desarrolladoras
    @GetMapping
    @Operation(summary = "listar desarrolladoras",description = "obtiene una lista de desarrolladoras")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "lista de desarrolladoras devuelta correctamente")})
    public ResponseEntity<List<DesarrolladoraDTO>> listarTodas(){
       List<DesarrolladoraDTO> lista= dService.listarTodos();
       return ResponseEntity.ok(lista);
    }
}

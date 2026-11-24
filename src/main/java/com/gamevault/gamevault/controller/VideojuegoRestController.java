package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDetalleDTO;
import com.gamevault.gamevault.dto.VideojuegoRequestDTO;
import com.gamevault.gamevault.service.VideojuegoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoRestController {
    private final VideojuegoService vService;

    public VideojuegoRestController(VideojuegoService vService) {
        this.vService = vService;
    }

    //RF 01 Listar con filtros
    @Operation(summary = "listar videojuegos",description = "obtiene una lista de videojuegos aplicando filtros opcionales")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "lista de videojuegos devuelta correctamente")})
    @GetMapping
    public ResponseEntity<List<VideojuegoDetalleDTO>> listar(@RequestParam(required = false) String titulo,@RequestParam(required = false) Integer idDesarrolladora,@RequestParam(required = false) Integer anioDesde,@RequestParam(required = false) Integer pegiMax,@RequestParam(required = false) boolean soloDisponibles){
        FiltroVideojuegosDTO filtro=new FiltroVideojuegosDTO(titulo,idDesarrolladora,anioDesde,pegiMax,soloDisponibles);
        List<VideojuegoDetalleDTO> lista=vService.buscar(filtro);
        return ResponseEntity.ok(lista);
    }

    //RF 02 Devolver videojuego
    @Operation(summary = "obtener videojuego por id",description = "devuelve los detalles de un videojuego específico")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "videojuego encontrado"),@ApiResponse(responseCode = "404",description = "videojuego no existe")})
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoDetalleDTO> devolverVideojuego(@PathVariable int id){
       VideojuegoDetalleDTO v= vService.buscarDetallePorId(id);
       return ResponseEntity.ok(v);
    }

    //RF 03 Crear videojuego
    @Operation(summary = "registrar videojuego",description = "registra un nuevo videojuego validando las reglas de negocio")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "videojuego registrado correctamente"),@ApiResponse(responseCode = "400",description = "datos no válidos"),@ApiResponse(responseCode = "404",description = "la desarrolladora no existe")})
    @PostMapping
    public ResponseEntity<VideojuegoDetalleDTO> crear(@RequestBody VideojuegoRequestDTO videojuego){
        VideojuegoDetalleDTO v=vService.insertar(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(v);
    }
}

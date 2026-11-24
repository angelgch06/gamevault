package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.dto.*;
import com.gamevault.gamevault.service.SocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioRestController {

    private final SocioService sService;


    public SocioRestController(SocioService sService) {
        this.sService = sService;
    }

    //RF 04 Listar socios con filtros
    @Operation(summary = "listar socios",description = "obtiene una lista de socios aplicando filtros opcionales")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "lista de socios devuelta correctamente")})
    @GetMapping
    public ResponseEntity<List<SocioDetalleDTO>> listar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String correo,  @RequestParam(required = false) boolean activo){
        FiltroSociosDTO filtro=new FiltroSociosDTO(nombre,correo,activo);
        List<SocioDetalleDTO> lista=sService.buscar(filtro);
        return ResponseEntity.ok(lista);
    }
    //RF 05 Devolver de socio
    @Operation(summary = "obtener videojuego por id",description = "devuelve los detalles de un socio específico")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "socio encontrado"),@ApiResponse(responseCode = "404",description = "socio no existe")})
    @GetMapping("/{id}")
    public ResponseEntity<SocioDetalleDTO> devolverSocio(@PathVariable int id){
        SocioDetalleDTO s= sService.buscarId(id);
        return ResponseEntity.ok(s);
    }

    //RF 06 Crear socio
    @Operation(summary = "registrar socio",description = "registra un nuevo socio validando las reglas de negocio")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "socio registrado correctamente"),@ApiResponse(responseCode = "400",description = "datos no válidos"),@ApiResponse(responseCode = "409",description = "el correo del socio ya existe")})
    @PostMapping
    public ResponseEntity<SocioDetalleDTO> crear(@RequestBody SocioRequestDTO socio){
        SocioDetalleDTO s=sService.insert(socio);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
    //RF 07 actualizar
    @Operation(summary = "actualizar un socio",description = "actualiza un socio validando las reglas de negocio")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "socio actualizado correctamente"),@ApiResponse(responseCode = "400",description = "datos no válidos"),@ApiResponse(responseCode = "404",description = "el socio no existe"),@ApiResponse(responseCode = "409",description = "el correo del socio ya existe")})
    @PutMapping("/{id}")
    public ResponseEntity<SocioDetalleDTO> actualizar(@PathVariable int id,@RequestBody SocioRequestDTO socio){
        SocioDetalleDTO s=sService.actualizarSocio(id,socio);
        return ResponseEntity.ok(s);
    }
    //RF 08 borrar
    @Operation(summary = "borrar un socio",description = "borra un socio validando que no tenga préstamos asociados")
    @ApiResponses(value = {@ApiResponse(responseCode = "204",description = "socio borrado correctamente"),@ApiResponse(responseCode = "404",description = "el socio no existe"),@ApiResponse(responseCode = "409",description = "el correo del socio ya existe")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id){
        sService.borrarSocio(id);
        return ResponseEntity.noContent().build();
    }
}

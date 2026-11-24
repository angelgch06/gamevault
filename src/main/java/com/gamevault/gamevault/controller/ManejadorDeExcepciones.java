package com.gamevault.gamevault.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ManejadorDeExcepciones {
    //400 BAD REQUEST

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> manejadorDeMalRequisito(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status",400,"error","bad request","message",ex.getMessage()));
    }
    //404 NOT FOUND

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,Object>> manejadorDeNoEncontrado(NoSuchElementException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"error","not found","message",ex.getMessage()));
    }
    //409 CONFLICT

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String,Object>> manejadorDeConflicto(IllegalStateException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("status",409,"error","conflict","message",ex.getMessage()));
    }
    //422 Entidad no procesable

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,Object>> manejadorDeNoProcesable(RuntimeException ex){
        if(ex.getMessage() != null && ex.getMessage().startsWith("RN")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("status",422,"error","unprocessable entity","message",ex.getMessage()));
        }

        //500 Error inesperado
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status",500,"error","internal server error","message",ex.getMessage()));

    }



}

package com.gamevault.gamevault.controller;



import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.VideojuegoDTO;
import com.gamevault.gamevault.service.DesarrolladoraService;
import com.gamevault.gamevault.service.VideojuegoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.MarshalledObject;


@Controller
@RequestMapping("/videojuegos")
public class VideojuegoController {
    private final VideojuegoService vService;
    private final DesarrolladoraService dService;

    public VideojuegoController(VideojuegoService vService, DesarrolladoraService dService) {
        this.vService = vService;
        this.dService = dService;
    }

    @GetMapping
    public String listar(@ModelAttribute ("filtro") FiltroVideojuegosDTO filtro, Model model){
        model.addAttribute("videojuegos",vService.buscar(filtro));
        model.addAttribute("desarrolladoras",dService.listarTodos());
        return "videojuegos/listado";
    }

    @GetMapping("/alta")
    public String mostrarAlta(Model modelo){
        modelo.addAttribute("videojuego",new VideojuegoDTO());
        modelo.addAttribute("desarrolladoras",dService.listarTodos());
        return "videojuegos/alta";
    }
    @PostMapping("/alta")
    public String alta(@ModelAttribute VideojuegoDTO v,Model modelo){
        try{
           int id= vService.insertar(v);
            modelo.addAttribute("nombreMensaje","videojuego "+v.getTitulo()+" insertado correctamente con id "+id);
            modelo.addAttribute("videojuego",new VideojuegoDTO());
        }catch(IllegalArgumentException e){
            modelo.addAttribute("error",e.getMessage());
            modelo.addAttribute("videojuego",v);
        }
        modelo.addAttribute("desarrolladoras",dService.listarTodos());
        return "videojuegos/alta";
    }
}

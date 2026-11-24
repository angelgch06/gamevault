package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.service.CopiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/copias")
public class CopiaController {
    private final CopiaService cService;


    public CopiaController(CopiaService cService) {
        this.cService = cService;
    }

    @GetMapping
    public String listado(Model modelo){
        modelo.addAttribute("copias",cService.listarTodos());
        return "copias/listado";
    }


}

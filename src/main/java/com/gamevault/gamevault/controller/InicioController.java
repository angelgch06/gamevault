package com.gamevault.gamevault.controller;


import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.service.CopiaService;
import com.gamevault.gamevault.service.PrestamoService;
import com.gamevault.gamevault.service.SocioService;
import com.gamevault.gamevault.service.VideojuegoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    private final VideojuegoService vService;
    private final SocioService sService;
    private final PrestamoService pService;
    //private final CopiaService cService;

    public InicioController(VideojuegoService vService, SocioService sService, PrestamoService pService, CopiaService cService) {
        this.vService = vService;
        this.sService = sService;
        this.pService = pService;
        //this.cService = cService;
    }

    @GetMapping({"/","/inicio"})
    public String inicio(Model modelo){
        modelo.addAttribute("numVideojuegos",vService.buscar(new FiltroVideojuegosDTO()).size());
        modelo.addAttribute("numSocioActivos",sService.contarActivos());
        modelo.addAttribute("numPrestamosAbiertos",pService.contarAbiertos());
        modelo.addAttribute("numPrestamosVencidos",pService.contarVencidos());
        return "inicio";

    }
}

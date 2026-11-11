package com.gamevault.gamevault.controller;


import com.gamevault.gamevault.dto.FiltroSociosDTO;
import com.gamevault.gamevault.dto.FiltroVideojuegosDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import com.gamevault.gamevault.service.CopiaService;
import com.gamevault.gamevault.service.PrestamoService;
import com.gamevault.gamevault.service.SocioService;
import com.gamevault.gamevault.service.VideojuegoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socios")
public class SocioController {
    private final SocioService sService;

    public SocioController(SocioService sService) {
        this.sService = sService;
    }

    @GetMapping
    public String listado(@ModelAttribute ("filtro") FiltroSociosDTO filtro, Model modelo){
        modelo.addAttribute("socios",sService.buscar(filtro));
        return "socios/listado";
    }

    @GetMapping("/alta")
    public String mostrarAlta(Model modelo){
        modelo.addAttribute("socio",new SocioDTO());
        return "socios/alta";
    }

    @PostMapping("/alta")
    public String alta(@ModelAttribute SocioDTO s, Model modelo){
        try{
            int id =sService.insert(s);
            modelo.addAttribute("mensaje","socio insertado correctamente "+id);
            modelo.addAttribute("socio",new SocioDTO());
        }catch(IllegalArgumentException e){
            modelo.addAttribute("error",e.getMessage());
            modelo.addAttribute("socio",s);
        }
        return "socios/alta";
    }

}

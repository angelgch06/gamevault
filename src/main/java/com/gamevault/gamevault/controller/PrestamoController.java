package com.gamevault.gamevault.controller;

import com.gamevault.gamevault.dto.FiltroPrestamosDTO;
import com.gamevault.gamevault.dto.PrestamoDTO;
import com.gamevault.gamevault.dto.PrestamoDetalleDTO;
import com.gamevault.gamevault.service.CopiaService;
import com.gamevault.gamevault.service.PrestamoService;
import com.gamevault.gamevault.service.SocioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    private final PrestamoService pService;
    private final SocioService sService;
    private final CopiaService cService;


    public PrestamoController(PrestamoService pService, SocioService sService, CopiaService cService) {
        this.pService = pService;
        this.sService = sService;
        this.cService = cService;
    }
    @GetMapping
    public String listado(@ModelAttribute ("filtro") FiltroPrestamosDTO filtro, Model modelo){
        modelo.addAttribute("prestamos",pService.buscar(filtro));
        return "prestamos/listado";
    }
    @GetMapping("/registrar")
    public String mostrarRegistrar(Model modelo){
        modelo.addAttribute("socios",sService.listarActivos());
        modelo.addAttribute("copias",cService.listarDisponibles());
        return "prestamos/registrar";
    }
    @PostMapping("/registrar")
    public String registrar(@RequestParam int idSocio, @RequestParam int idCopia, Model modelo){
        try{
            PrestamoDetalleDTO p= pService.insert(idSocio,idCopia);
            modelo.addAttribute("prestamoCreado",p);
            //nos devuelve el objeto creado
        }catch(IllegalArgumentException e){
            modelo.addAttribute("error",e.getMessage());
            //solo selecciona asique no hay que pasarle el objeto guardado
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        modelo.addAttribute("socios", sService.listarActivos());
        modelo.addAttribute("copias",cService.listarDisponibles());
        return "prestamos/registrar";
    }
    @GetMapping("/devolver")
    public String mostrarDevolver(@RequestParam (required = false) String socio,Model modelo){
        modelo.addAttribute("prestamosAbiertos",pService.listarAbiertos(socio));
        modelo.addAttribute("socio",socio);
        return "prestamos/devolver";
    }
    @PostMapping("/devolver")
    public String devolver(@RequestParam int idPrestamo, @RequestParam (defaultValue = "false") boolean copiaDanada, Model modelo){
        try{
           PrestamoDetalleDTO p= pService.devolver(idPrestamo,copiaDanada);
           modelo.addAttribute("prestamoDevuelto",p);
        }catch(IllegalArgumentException e){
            modelo.addAttribute("error",e.getMessage());
        } catch (SQLException e) {
            modelo.addAttribute("error",e.getMessage());
        }
        modelo.addAttribute("prestamosAbiertos",pService.listarAbiertos(null));
        return "prestamos/devolver";
    }

}

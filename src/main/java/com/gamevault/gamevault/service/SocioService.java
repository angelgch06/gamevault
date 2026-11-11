package com.gamevault.gamevault.service;

import com.gamevault.gamevault.DAO.ISocioDAO;
import com.gamevault.gamevault.dto.FiltroSociosDTO;
import com.gamevault.gamevault.dto.PrestamoDTO;
import com.gamevault.gamevault.dto.SocioDTO;
import com.gamevault.gamevault.dto.SocioDetalleDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SocioService {
    private final ISocioDAO dao;


    public SocioService(ISocioDAO dao) {
        this.dao = dao;
    }

    public List<SocioDetalleDTO> buscar(FiltroSociosDTO filtro){
        return dao.buscar(filtro);
    }
    public int insert(SocioDTO s){
        if(s.getNombre()==null || s.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre es obligatiorio");
        }
        if(s.getApellidos()==null || s.getApellidos().isBlank()){
            throw new IllegalArgumentException("Los apellidos son obligatiorios");
        }
        if(s.getEmail()==null || s.getEmail().isBlank()){
            throw new IllegalArgumentException("El correo es obligatiorio");
        }
        if(s.getFechaNacimiento()==null){
            throw new IllegalArgumentException("La fecha de nacimiento es obligatioria");
        }
        if(s.getFechaNacimiento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        if(dao.existePorCorreo(s.getEmail())){
            throw new IllegalArgumentException("Ya existe un socio con ese correo");
        }
        s.setFechaAlta(LocalDate.now());
        s.setActivo(true);
        return dao.insert(s);
    }
    public int contarActivos(){
       return dao.contarActivos();
    }
    public List<SocioDTO>listarActivos(){
        return dao.listarActivos();
    }

}
